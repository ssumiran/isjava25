package j.jav25.aexercise.todomgmt.service;

import java.util.List;

import j.jav25.aexercise.todomgmt.dto.TodoDto;

public interface TodoService {
	
	TodoDto addTodo(TodoDto todoDto);
	List<TodoDto> getAllTodos();
	TodoDto getTodoById(Long id);

}
