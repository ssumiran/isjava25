package j.jav25.security.exception;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, 
						HttpServletResponse response,
						AccessDeniedException accessDeniedException) throws IOException, ServletException {

		response.setHeader("error-reason", "Authorization failed");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());

        SecErrorResponse secErrorResponse = 
        		new SecErrorResponse(
        			LocalDateTime.now(),
	        		HttpStatus.FORBIDDEN.getReasonPhrase(),
	        		accessDeniedException.getMessage(),
	        		HttpStatus.FORBIDDEN.value(),
	        		request.getRequestURI()
	        	);
        response.getWriter().write(new ObjectMapper().writeValueAsString(secErrorResponse));
	}
}
