package j.jav25.userpost.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import j.jav25.exception.BadRequestException;
import j.jav25.exception.ResourceNotFoundException;
import j.jav25.userpost.dto.PostDto;
import j.jav25.userpost.entity.Post;
import j.jav25.userpost.entity.User;
import j.jav25.userpost.repository.PostRepository;
import j.jav25.userpost.repository.UserRepository;
import j.jav25.userpost.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	private PostRepository postRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    // @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto addPost(Long userId, PostDto postDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Post post = modelMapper.map(postDto, Post.class);
        post.setUser(user);

        Post savedPost = postRepository.save(post);

        PostDto savedPostDto = modelMapper.map(savedPost, PostDto.class);
        savedPostDto.setUserId(savedPost.getUser().getId());

        return savedPostDto;
    }

    @Override
    public PostDto getPostById(Long userId, Long postId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        if(!post.getUser().getId().equals(user.getId())){
            throw new ResourceNotFoundException("This post does not belong to the user with id " + userId);
        }

        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setUserId(post.getUser().getId());

        return postDto;
    }

    @Override
    public List<PostDto> getAllPostsByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Post> posts = postRepository.findByUserId(userId);

        return posts.stream().map((post) -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto updatePost(Long userId, Long postId, PostDto postDto) {

        // check if user exists in the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id" + userId));

        // retrieve the post
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));


        if (!post.getUser().getId().equals(user.getId())){
            throw new BadRequestException("This post does not belong to the user with id " + userId);
        }

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost =  postRepository.save(post); // update
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Long userId, Long postId) {
        // check if user exists in the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id" + userId));

        // retrieve the post
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));


        if (!post.getUser().getId().equals(user.getId())){
            throw new BadRequestException("This post does not belong to the user with id " + userId);
        }

        postRepository.delete(post);
    }
}
