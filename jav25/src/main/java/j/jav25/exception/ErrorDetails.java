package j.jav25.exception;

import java.time.LocalDateTime;

public record ErrorDetails(
		LocalDateTime timestamp,
		String error,		
		int status,
		String messages,
		String path
){	
}
