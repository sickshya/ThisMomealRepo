package co.doeat.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		log.warn("Login Success");
		List<String> roleNames = new ArrayList<>();

		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		log.warn("ROLE NAMES : " + roleNames);

		if (roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("admin/adminChallenge");
			return;
		}
		
		if (roleNames.contains("ROLE_USER")) {
			response.sendRedirect("/home");
			return;
		}
		response.sendRedirect("/");
	}

}
