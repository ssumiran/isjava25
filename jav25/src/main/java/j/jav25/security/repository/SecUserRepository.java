package j.jav25.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import j.jav25.security.entity.SecUser;

public interface SecUserRepository extends JpaRepository<SecUser, Long>{

	Optional<SecUser> findByUsername(String username);

    Optional<SecUser> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
