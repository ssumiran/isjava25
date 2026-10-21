package j.jav25.security.dto;

public record LoginDto(
	String usernameOrEmail,
	String password
) {

}
