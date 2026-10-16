package j.jav25.userpost.service;

import java.util.List;

import j.jav25.userpost.dto.PostDto;

public interface PostService {
	PostDto addPost(Long userId, PostDto postDto);

    PostDto getPostById(Long userId, Long postId);

    List<PostDto> getAllPostsByUserId(Long userId);

    PostDto updatePost(Long userId, Long postId, PostDto postDto);

    void deletePost(Long userId, Long postId);
}
