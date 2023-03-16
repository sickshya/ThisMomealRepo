package co.doeat.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.info("+++++++++++++++++++++++++++++++++++++++++++++++++"+HttpServletResponse.SC_UNAUTHORIZED);
		if(accessDeniedException instanceof AccessDeniedException) {
			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
		response.sendRedirect("/");
		
	}
	
}
