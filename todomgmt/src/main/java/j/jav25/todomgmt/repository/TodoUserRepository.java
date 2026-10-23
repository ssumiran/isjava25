package j.jav25.todomgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import j.jav25.todomgmt.entity.TodoUser;

public interface TodoUserRepository extends JpaRepository<TodoUser, Long> {

	Optional<TodoUser> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<TodoUser> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);
}
