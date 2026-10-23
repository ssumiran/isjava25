package j.jav25.todomgmt.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TodoApiException  extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
	private HttpStatus status;
    private String message;
}
