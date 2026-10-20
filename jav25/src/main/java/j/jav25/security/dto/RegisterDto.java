package j.jav25.security.dto;

import java.util.Set;

public record RegisterDto (
	Long id,
	String name,
	String username,
	String password,
	String email,
	Set<String> roles
) {	
}
