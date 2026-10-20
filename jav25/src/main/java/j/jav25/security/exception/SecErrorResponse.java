package j.jav25.security.exception;

import java.time.LocalDateTime;

public record SecErrorResponse(
		LocalDateTime timestamp,
	    String error,
	    String message,
	    int status,
	    String path
	    
) {

}
