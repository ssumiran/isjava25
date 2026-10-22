package j.jav25.aexercise.todomgmt.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import j.jav25.aexercise.todomgmt.dto.TodoDto;
import j.jav25.aexercise.todomgmt.entity.Todo;
import j.jav25.aexercise.todomgmt.exception.ResourceNotFoundException;
import j.jav25.aexercise.todomgmt.repository.TodoRepository;
import j.jav25.aexercise.todomgmt.service.TodoService;


@Service
public class TodoServiceImpl implements TodoService{

    private ModelMapper modelMapper;

	private TodoRepository todoRepository;
	
	public TodoServiceImpl ( TodoRepository todoRepository, ModelMapper modelMapper) {
		this.todoRepository = todoRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		
		//convert TodoDto into Todo entity  Todo todo = modelMapper.map(todoDto, Todo.class);
		Todo todo = convertDtoToEntity(todoDto);
		
     	//save to Db
        Todo save = todoRepository.save(todo);

        //return save into dto object  modelMapper.map(save, TodoDto.class);
        return convertEntityToDto(save);
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		return todos.stream()
				.map( (t) -> convertEntityToDto(t) )
                .collect(Collectors.toList());
	}
	
	public TodoDto getTodoById(Long id) {
		Todo todo = todoRepository.findById(id)
					.orElseThrow(
						() -> new ResourceNotFoundException("Todo is not found with id:" + id)
					);
		
		return convertEntityToDto(todo);
	}
	
	private Todo convertDtoToEntity (TodoDto t) {
		Todo todo = new Todo(
				t.title(),
				t.description(),
				t.completed()
				);
		return todo;
	}
	
	private TodoDto convertEntityToDto (Todo todo) {
		TodoDto t = new TodoDto(
				todo.getId(),
				todo.getTitle(),
				todo.getDescription(), 
				todo.isCompleted()
				);
		return t;
	}
}
