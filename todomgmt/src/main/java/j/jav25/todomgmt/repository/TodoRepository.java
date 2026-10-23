package j.jav25.todomgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j.jav25.todomgmt.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
