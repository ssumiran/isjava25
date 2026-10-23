package j.jav25.todomgmt.dto;

public record TodoRegisterDto(
		String name,
	    String username,
	    String email,
	    String password
) {

}
