package j.jav25.userpost.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import j.jav25.userpost.dto.PostDto;
import j.jav25.userpost.service.PostService;

@RestController
@RequestMapping("/api/users")
public class PostController {

    private PostService postService;

    // @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/{userId}/posts")
    public ResponseEntity<PostDto> addPost(@PathVariable Long userId,
                                           @RequestBody PostDto postDto){
         PostDto savedPost = postService.addPost(userId, postDto);
         return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long userId,
                                               @PathVariable("id") Long postId){
        PostDto postDto = postService.getPostById(userId, postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable Long userId){
        List<PostDto> posts = postService.getAllPostsByUserId(userId);
//        return new ResponseEntity<>(posts, HttpStatus.OK);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{userId}/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long userId,
                                              @PathVariable("id") Long postId,
                                              @RequestBody PostDto postDto){

        PostDto updatedPost = postService.updatePost(userId, postId, postDto);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("{userId}/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long userId,
                                             @PathVariable("id") Long postId){
        postService.deletePost(userId, postId);
        return ResponseEntity.ok("Post deleted successfully!");
    }
}
