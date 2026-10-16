package j.jav25.userpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import j.jav25.userpost.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	List<Post> findByUserId(Long userId);
}
