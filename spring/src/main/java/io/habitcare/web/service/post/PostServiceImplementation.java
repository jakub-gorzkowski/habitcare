package io.habitcare.web.service.post;

import io.habitcare.web.dto.PostDto;
import io.habitcare.web.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImplementation implements PostService {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImplementation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findPostsByUserId(Long userId) {
        return postRepository.findPostByUserId(userId);
    }
}
