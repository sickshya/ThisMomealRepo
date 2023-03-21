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
		http.authorizeHttpRequests((requests) -> requests
				.antMatchers("/", "/index*", "/home", "/main", "/notice**", "/mm_images/**",
						"/login*", "/logout*", "/error", "/signup/**", 
						"/contentsMain/**", "/contentsDetail/**")
				.permitAll()
				// ▲ 비로그인 유저까지 허가되는 접근 url
				.antMatchers("/usr/**", "/chlg/**", "/pch/**", "/exp/**", "/fllw/**", "/cmt/**", "/cty/**")
				.hasAnyAuthority("ROLE_USER","ROLE_ADMIN") // 일반유저 접근 허용 경로( + 관리자 허용 )
				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // 관리자권한 접근 허용 경로
				.anyRequest().authenticated()) // permitAll 외의 요청엔 로그인 요구
				.rememberMe((remember) -> remember.tokenValiditySeconds(86400 + 43200) // token 유효시간, 36시간
						.rememberMeParameter("remember-me") // 로그인정보 저장
						.tokenRepository(PersistentTokenRepository()))
				.formLogin((form) -> form.loginPage("/login").permitAll() // 접근이 차단된 페이지를 클릭할 시 이동할 url
						.loginProcessingUrl("/doLogin") // 로그인동작 시 매핑되는 url
						.usernameParameter("userId") // 로그인 view 내의 form 태그 내에 로그인 할 때 username에 매핑되는 태그의 name
						.passwordParameter("password") // 로그인 view 내의 form 태그 내에 로그인 할 때 password에 매핑되는 태그의 name
						.defaultSuccessUrl("/home")
						.successHandler(new CustomLoginSuccessHandler()) // 로그인 성공시 실행되는 핸들러
						.failureUrl("/login?error") // 로그인 실패시 url
						.failureHandler(new CustomLoginFailureHandler()) // 로그인 실패시 실행되는 핸들러
				)
//				.sessionManagement(session -> session.maximumSessions(5)) // 동시 접속자 수 5
				.exceptionHandling((denied) -> denied.accessDeniedPage("/users/accessError"))
				.logout(
						(logout) -> logout
						.permitAll()
						.logoutUrl("/logout") // 로그아웃 시 맵핑되는 url
						.logoutSuccessUrl("/login") // 로그아웃 성공 시 리다이렉트 주소
						.invalidateHttpSession(true) // session clear
						.deleteCookies("remember-me", "JSESSION_ID"))
				.csrf().disable();
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		// Spring Security를 적용하지 않을 리소스를 설정
		return web -> {
			web.ignoring()
			.antMatchers(
					"/css/**", "/assets/**", "/data/**", "/fonts/**", "/js/**", "/massets/**",
					"/modal-template/**", "/second-template/**");
		};
	}

	// static에 있는 파일은 시큐리티를 무시해주는 설정인데 일단 후처리
//	@Bean
//	public void configure(WebSecurity web) throws Exception {
//	       web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//	   }
}