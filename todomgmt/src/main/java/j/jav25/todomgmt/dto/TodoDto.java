package j.jav25.todomgmt.dto;

public record TodoDto(
	Long id,
	String title,
	String description,
	boolean completed
){
}
