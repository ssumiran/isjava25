package j.jav25.exception;

import java.time.LocalDateTime;

public record ErrorDetails(
		LocalDateTime timestamp,
		String messages,
		String details,
		String errorCode) 
{}
