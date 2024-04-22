package io.habitcare.web.service.post;

import io.habitcare.web.dto.PostDto;
import io.habitcare.web.mapper.PostMapper;
import io.habitcare.web.model.Post;
import io.habitcare.web.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImplementation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public boolean exists(Long postId) {
        return postRepository.existsById(postId);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<PostDto> readUserPosts(Long userId) {
        return postRepository.findByUserId(userId).stream()
                .map(PostMapper::mapToPostDto)
                .toList();
    }

    @Override
    public Post updatePost(Long postId, Post post) {
        post.setId(postId);
        return postRepository.findById(postId).map(existingPost -> {
            Optional.ofNullable(post.getDescription()).ifPresent(existingPost::setDescription);
            Optional.ofNullable(post.getPostDate()).ifPresent(existingPost::setPostDate);
            Optional.ofNullable(post.getMood()).ifPresent(existingPost::setMood);
            return postRepository.save(existingPost);
        }).orElseThrow(() -> new RuntimeException("Post does not exist"));
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
