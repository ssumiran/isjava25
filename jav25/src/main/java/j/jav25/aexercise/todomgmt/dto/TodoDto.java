package j.jav25.aexercise.todomgmt.dto;

public record TodoDto(
	Long id,
	String title,
	String description,
	boolean completed
){
}
