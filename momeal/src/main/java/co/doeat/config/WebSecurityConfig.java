package co.doeat.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

/*
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable() //csrf 토큰검사를 비활성화
		.authorizeHttpRequests(
				(requests) -> requests.antMatchers("/", "/home", "/assets/**", "/css/**").permitAll() // 설정된 url은 인증되지 않더라도 누구나 접근 가능
				.anyRequest().authenticated()
				)
		.authorizeHttpRequests((requests) -> requests.antMatchers("/admin"))// 위 페이지 외에는 인증이 되어야 접근 가능
		.formLogin(
				(form) -> form.loginPage("/login") // 접근이 차단된 페이지를 클릭할 시 이동할 url
				.loginProcessingUrl("/??") // 로그인 시 매핑되는 url
				.usernameParameter("username") // 로그인 view 내의 form 태그 내에 로그인 할 때 userId에 매핑되는 태그의 name
				.passwordParameter("password") // 로그인 view 내의 form 태그 내에 로그인 할 때 pwd에 매핑되는 태그의 name
				.successHandler(new CustomLoginSuccessHandler()) // 로그인 성공시 실행되는 메소드
				.permitAll())
		.logout(
				(logout) -> logout.permitAll()
				.logoutUrl("/logout") // 로그아웃 시 맵핑되는 url
				.logoutSuccessUrl("/") // 로그아웃 성공 시 리다이렉트 주소
				.invalidateHttpSession(true) // session clear
				);
		return http.build();
	}
*/
	
/*
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// 세션을 사용하지 않고 JWT 토큰을 활용하여 진행, csrf토큰검사를 비활성화
                .authorizeRequests() // 인증절차에 대한 설정을 진행
                .antMatchers("/", "/error/*", "/login", "/loginProc").permitAll() // 설정된 url은 인증되지 않더라도 누구든 접근 가능
                .anyRequest().authenticated()// 위 페이지 외 인증이 되어야 접근가능(ROLE에 상관없이)
                .and()
                .formLogin().loginPage("/login")  // 접근이 차단된 페이지 클릭시 이동할 url
                .loginProcessingUrl("/loginProc") // 로그인시 맵핑되는 url
                .usernameParameter("userId")      // view form 태그 내에 로그인 할 id 에 맵핑되는 name ( form 의 name )
                .passwordParameter("userPw")      // view form 태그 내에 로그인 할 password 에 맵핑되는 name ( form 의 name )
                .successHandler(successHandlerHandler()) // 로그인 성공시 실행되는 메소드
                .failureHandler(failureHandlerHandler()) // 로그인 실패시 실행되는 메소드
                .permitAll()
                .and()
                .logout() // 로그아웃 설정
                .logoutUrl("/logout") // 로그아웃시 맵핑되는 url
                .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true); // 세션 clear
    }
*/
}