package j.jav25.aexercise.todomgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import j.jav25.aexercise.todomgmt.dto.TodoDto;
import j.jav25.aexercise.todomgmt.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	//@Autowired
	private TodoService todoService;
	
	public TodoController (TodoService todoService ) {
		this.todoService = todoService;
	}
	
	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
		TodoDto savedTodoDto = todoService.addTodo(todoDto);
		
		return ResponseEntity.ok(savedTodoDto);
	}
	
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos() {
		List<TodoDto> todos = todoService.getAllTodos();
		return ResponseEntity.ok(todos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id) {
		TodoDto todo = todoService.getTodoById(id);
		return ResponseEntity.ok(todo);
	}

}
