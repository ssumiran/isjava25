package j.jav25.taskmanagement.service;

import java.util.List;

import j.jav25.taskmanagement.dto.TaskDto;

public interface TaskService {
	TaskDto addTask(TaskDto taskDto);

    TaskDto getTask(Long id);

    List<TaskDto> getAllTasks();

    TaskDto updateTask(TaskDto taskDto, Long id);

    void deleteTask(Long id);

    TaskDto completeTask(Long id);

    TaskDto inCompleteTask(Long id);
}

