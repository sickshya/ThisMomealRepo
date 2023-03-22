package co.doeat.config.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String errorMessage;

		if (exception instanceof BadCredentialsException) {
			errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			errorMessage = "내부 시스템 문제로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요. ";
		} else if (exception instanceof UsernameNotFoundException) {
			errorMessage = "존재하지 않는 계정입니다. 회원가입 후 로그인해주세요.";
		} else if (exception instanceof AuthenticationCredentialsNotFoundException) {
			errorMessage = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
		} else if (exception instanceof DisabledException) {
			errorMessage = "탈퇴후 이용할 수 없는 아이디입니다.";
		} else {
			errorMessage = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
		}

		errorMessage = URLEncoder.encode(errorMessage, "UTF-8"); /* 한글 인코딩 깨진 문제 방지 */
		setDefaultFailureUrl("/login?error=true&exception=" + errorMessage); // 실패시 이동할 url

		request.setAttribute("exception", exception.getMessage());
		request.setAttribute("errorMessage", errorMessage);
		super.onAuthenticationFailure(request, response, exception);
	}

}

//		private final String DEFAULT_FAILURE_URL = "/login?error=true";
//		setDefaultFailureUrl("/login?error=true&exception=" + exception.getMessage());

//		setDefaultFailureUrl(DEFAULT_FAILURE_URL);

// request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request,
// response);;

/*
 * CustomAuthenticationFailureHandler는 super 클래스를 호출하지 않아도 상관없다. 다만 실패 이후 이동할
 * 페이지를 지정할 경우 상위클래스에게 위임하는 것이 더 좋기 때문에 super.onAuthenticationFailure 처리 실패와 관련된
 * 여러가지 후속 처리를 해주기 때문....
 */
//		response.sendRedirect("/login?error=" + errorMessage);
