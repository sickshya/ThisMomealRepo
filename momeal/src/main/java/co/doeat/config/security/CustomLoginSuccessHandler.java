package co.doeat.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("userId", authentication.getName());

		/*
		 * // 경우1 : 로그인 인증을 위해 Spring Security가 요청을 가로챈 경우 RequestCache requestCache =
		 * new HttpSessionRequestCache(); SavedRequest savedRequest =
		 * requestCache.getRequest(request, response);
		 * 
		 * // 경우2 : 사용자가 직접 로그인 버튼을 누른 경우 String prevPage = (String)
		 * request.getSession().getAttribute("prevPage"); if (prevPage != null) {
		 * request.getSession().removeAttribute("prevPage"); }
		 * 
		 * String uri = request.getContextPath(); // 경우1 일 경우 uri에 경로 저장 if
		 * (savedRequest != null) uri = savedRequest.getRedirectUrl(); // 경우2 일 경우 uri에
		 * 경로 저장 else if (prevPage != null) uri = prevPage;
		 * 
		 */

		List<String> roleNames = new ArrayList<>();

		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});

		if (roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("admin/adminChallenge");
			return;
		}

		if (roleNames.contains("ROLE_USER")) {
			response.sendRedirect("/usr/myFeed/" + authentication.getName());
			return;
		}

		response.sendRedirect("/");
//		response.sendRedirect(uri);
	}

}
