package io.habitcare.web.service.post;

import io.habitcare.web.dto.PostDto;
import io.habitcare.web.model.Post;

import java.util.List;

public interface PostService {
    boolean exists(Long postId);
    Post createPost(Post post);
    List<PostDto> readUserPosts(Long userId);
    Post updatePost(Long postId, Post post);
    void deletePost(Long postId);
}
