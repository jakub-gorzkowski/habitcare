package io.habitcare.web.mapper;

import io.habitcare.web.dto.MoodDto;
import io.habitcare.web.model.Mood;

public class MoodMapper {
    public static Mood mapFromMoodDto(MoodDto moodDto) {
        return Mood.builder()
                .id(moodDto.getId())
                .name(moodDto.getName())
                .value(moodDto.getValue())
                .build();
    }

    public static MoodDto mapToMoodDto(Mood mood) {
        return MoodDto.builder()
                .id(mood.getId())
                .name(mood.getName())
                .value(mood.getValue())
                .build();
    }
}
