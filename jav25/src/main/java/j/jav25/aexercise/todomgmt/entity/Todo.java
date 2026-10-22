package j.jav25.aexercise.todomgmt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "todos")
public class Todo {

	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column( nullable = false)
	private String title;
	
	@Column( nullable = false)
	private String description;
	private boolean completed;
	
	public Todo (String title, String description, boolean completed) {
		this.title = title;
		this.description = description;
		this.completed = completed;
	}
}
