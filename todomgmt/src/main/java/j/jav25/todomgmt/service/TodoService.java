package j.jav25.todomgmt.service;

import java.util.List;

import j.jav25.todomgmt.dto.TodoDto;

public interface TodoService {
	
	TodoDto addTodo(TodoDto todoDto);
	List<TodoDto> getAllTodos();
	TodoDto getTodoById(Long id);
	
	TodoDto updateTodo(TodoDto todoDto, Long id);
	
    void deleteTodo(Long id);
    
    TodoDto completeTodo(Long id);

    TodoDto inCompleteTodo(Long id);
}
