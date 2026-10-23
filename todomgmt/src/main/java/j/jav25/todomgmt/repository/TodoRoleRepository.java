package j.jav25.todomgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j.jav25.todomgmt.entity.TodoRole;

public interface TodoRoleRepository extends JpaRepository<TodoRole, Long> {

	TodoRole findByName(String name);
}
