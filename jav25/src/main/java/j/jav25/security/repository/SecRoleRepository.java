package j.jav25.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j.jav25.security.entity.SecRole;

public interface SecRoleRepository extends JpaRepository<SecRole, Long>{
	
	SecRole findByName(String name);

}
