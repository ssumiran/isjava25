package j.jav25.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import j.jav25.taskmanagement.entity.Task;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
}
