package io.habitcare.web.mapper;

import io.habitcare.web.dto.PostDto;
import io.habitcare.web.model.Mood;
import io.habitcare.web.model.Post;
import io.habitcare.web.model.User;

import static io.habitcare.web.mapper.MoodMapper.mapToMoodDto;
import static io.habitcare.web.mapper.UserMapper.mapToUserDto;

public class PostMapper {
    public static Post mapFromPostDto(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .description(postDto.getDescription())
                .postDate(postDto.getPostDate())
                .mood(Mood.builder()
                        .id(postDto.getMoodId())
                        .build())
                .user(User.builder()
                        .id(postDto.getUserId())
                        .build())
                .build();
    }

    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .description(post.getDescription())
                .postDate(post.getPostDate())
                .moodId(mapToMoodDto(post.getMood()).getId())
                .userId(mapToUserDto(post.getUser()).getId())
                .build();
    }
}
