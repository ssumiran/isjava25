package j.jav25.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import j.jav25.security.exception.EmailAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	//Handle specific Exception - AccountException
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<ErrorDetails> handleAccountException(
												AccountException ex, WebRequest webReq){
		
		ErrorDetails errorDetails = new ErrorDetails (
				LocalDateTime.now(),
				"Account Not FOUND",
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage(),
				webReq.getDescription(false)
			);
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	//Handle Generic Exception
	//@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGenericException(
			Exception ex, WebRequest webReq){
		
		ErrorDetails errorDetails = new ErrorDetails (
				LocalDateTime.now(),
				"Intenal Server Error",
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex.getMessage(),
				webReq.getDescription(false)
			);
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Handle Register username not found Exception
	@ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUsernameNotFoundException(UsernameNotFoundException ex,
                                                                        WebRequest webReq){
		ErrorDetails errorDetails = new ErrorDetails(
        		LocalDateTime.now(),
        		HttpStatus.BAD_REQUEST.getReasonPhrase(),
				HttpStatus.BAD_REQUEST.value(),
				ex.getMessage(),
				webReq.getDescription(false)
			);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

	//Handle Register email is existed Exception
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex,
                                                                        WebRequest webReq){
        ErrorDetails errorDetails = new ErrorDetails(
        		LocalDateTime.now(),
        		HttpStatus.BAD_REQUEST.getReasonPhrase(),
				HttpStatus.BAD_REQUEST.value(),
				ex.getMessage(),
				webReq.getDescription(false)
			);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	
	
}
