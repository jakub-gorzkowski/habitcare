package io.habitcare.web.controller;

import io.habitcare.web.dto.PostDto;
import io.habitcare.web.model.Post;
import io.habitcare.web.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.habitcare.web.mapper.PostMapper.mapFromPostDto;
import static io.habitcare.web.mapper.PostMapper.mapToPostDto;

@RestController
@RequestMapping(path = "/api/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<PostDto> addUserPost(@RequestBody PostDto postDto) {
        Post post = mapFromPostDto(postDto);
        Post savedPost = postService.createPost(post);
        return new ResponseEntity<>(mapToPostDto(savedPost), HttpStatus.CREATED);
    }

    @GetMapping(path = "/get/{userId}")
    public ResponseEntity<List<PostDto>> getUserPosts(@PathVariable Long userId) {
        List<PostDto> postDtoList = postService.readUserPosts(userId);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @PatchMapping(path = "/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        if (!postService.exists(postId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postDto.setId(postId);
        Post post = mapFromPostDto(postDto);
        Post updatedPost = postService.updatePost(postId, post);
        return new ResponseEntity<>(mapToPostDto(updatedPost), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{postId}")
    public ResponseEntity<PostDto> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
