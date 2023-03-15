package co.doeat.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	DataSource dataSource;

	@Bean
	public PersistentTokenRepository PersistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.antMatchers("/", "home", "/pch/**", "/signup/**", "/usrs/**", "/grps/**").permitAll()
				// ▲ 설정된 url은 인증되지 않더라도 누구나 접근 가능
				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // /admin 이하의 접근은 인증되어야함을 명시, 관리자권한 요구
				.anyRequest().authenticated() // 어떤 요청에도 보안검사를 한다?
		).rememberMe((remember) -> remember.tokenValiditySeconds(86400 + 43200) // token 유효시간, 36시간
				.rememberMeParameter("remember-me").tokenRepository(PersistentTokenRepository()))
				// 위 페이지 외에는 인증이 되어야 접근 가능
				.formLogin((form) -> form.loginPage("/login") // 접근이 차단된 페이지를 클릭할 시 이동할 url
						.loginProcessingUrl("/doLogin") // 로그인 시 매핑되는 url
						.usernameParameter("userId") // 로그인 view 내의 form 태그 내에 로그인 할 때 username에 매핑되는 태그의 name
						.passwordParameter("password") // 로그인 view 내의 form 태그 내에 로그인 할 때 password에 매핑되는 태그의 name
						.successHandler(new CustomLoginSuccessHandler()) // 로그인 성공시 실행되는 메소드
						.permitAll())
				.logout((logout) -> logout.permitAll().logoutUrl("/logout") // 로그아웃 시 맵핑되는 url
//						.logoutSuccessUrl("/") // 로그아웃 성공 시 리다이렉트 주소
						.invalidateHttpSession(true) // session clear
						.deleteCookies("remember-me", "JSESSION_ID")
				).csrf().disable();
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		// Spring Security를 적용하지 않을 리소스를 설정
		return web -> {
			web.ignoring().antMatchers("/css/**", "/assets/**", "/data/**", "/fonts/**", "/js/**", "/massets/**",
					"/modal-template/**", "/second-template/**");
		};
	}

	// static에 있는 파일은 시큐리티를 무시해주는 설정인데 일단 후처리
//	@Bean
//	public void configure(WebSecurity web) throws Exception {
//	       web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//	   }
}