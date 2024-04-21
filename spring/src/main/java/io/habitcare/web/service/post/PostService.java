package io.habitcare.web.service.post;

import io.habitcare.web.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findPostsByUserId(Long userId);
}
