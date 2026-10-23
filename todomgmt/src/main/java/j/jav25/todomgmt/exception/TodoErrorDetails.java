package j.jav25.todomgmt.exception;

import java.time.LocalDateTime;

public record TodoErrorDetails(
		LocalDateTime timestamp,
		int status,
		String error,
		String messages,
		String path
){	
}
