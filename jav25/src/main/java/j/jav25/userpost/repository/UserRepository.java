package j.jav25.userpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j.jav25.userpost.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
