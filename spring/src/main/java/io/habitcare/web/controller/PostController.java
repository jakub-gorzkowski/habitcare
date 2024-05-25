package io.habitcare.web.controller;

import io.habitcare.web.dto.PostDto;
import io.habitcare.web.model.Post;
import io.habitcare.web.service.jwt.JwtService;
import io.habitcare.web.service.post.PostService;
import io.habitcare.web.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static io.habitcare.web.mapper.PostMapper.mapFromPostDto;
import static io.habitcare.web.mapper.PostMapper.mapToPostDto;

@RestController
@RequestMapping(path = "/api/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    PostController(PostService postService, UserService userService, JwtService jwtService) {
        this.postService = postService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addUserPost(@RequestBody PostDto postDto, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);

        postDto.setUserId(userId);
        Post post = mapFromPostDto(postDto);
        try {
            Post savedPost = postService.createPost(post);
            return new ResponseEntity<>(mapToPostDto(savedPost), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<PostDto>> getUserPosts(@RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);

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

    @GetMapping(path = "/get-by-date/{date}")
    public ResponseEntity<PostDto> getUserPostByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        try {
            Post post = postService.getUserPostByDate(userId, startOfDay, endOfDay);
            return new ResponseEntity<>(mapToPostDto(post), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
