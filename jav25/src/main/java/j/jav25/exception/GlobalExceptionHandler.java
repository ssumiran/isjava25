package j.jav25.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	public ResponseEntity<ErrorDetails> handleAccountException(
			AccountException ex, WebRequest webReq){
		
		ErrorDetails errorDetails = new ErrorDetails (
				LocalDateTime.now(),
				ex.getMessage()
				webReq.getDescription("false"), 
				"Account Not FOUND");
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.CONFLICT);
	}
}
