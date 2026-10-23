package j.jav25.todomgmt.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import j.jav25.todomgmt.dto.TodoDto;
import j.jav25.todomgmt.entity.Todo;
import j.jav25.todomgmt.exception.ResourceNotFoundException;
import j.jav25.todomgmt.repository.TodoRepository;
import j.jav25.todomgmt.service.TodoService;


@Service
public class TodoServiceImpl implements TodoService{

	private TodoRepository todoRepository;
	
	public TodoServiceImpl ( TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
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
	
	@Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

         Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
         
         todo.setTitle(todoDto.title());
         todo.setDescription(todoDto.description());
         todo.setCompleted(todoDto.completed());

         Todo updatedTodo = todoRepository.save(todo);

        return convertEntityToDto(updatedTodo);
    }
	
	@Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todoRepository.deleteById(id);
    }
	
	@Override
    public TodoDto completeTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);

        return convertEntityToDto(updatedTodo);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);

        return convertEntityToDto(updatedTodo);
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
