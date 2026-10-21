package j.jav25.security.security;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import j.jav25.security.exception.SecErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, 
						HttpServletResponse response,
						AuthenticationException authException) throws IOException, ServletException {
		
		response.setHeader("error-reason", "Unauthorized");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        SecErrorResponse secErrorResponse = 
        		new SecErrorResponse(
				   	LocalDateTime.now(),
	        		HttpStatus.UNAUTHORIZED.getReasonPhrase(),
	        		authException.getMessage(),
	        		HttpStatus.UNAUTHORIZED.value(),
	        		request.getRequestURI()
	        	);
        //System.out.println("secErrorResponse: "+secErrorResponse);
        response.getWriter().write(new ObjectMapper().writeValueAsString(secErrorResponse));
	}
}
