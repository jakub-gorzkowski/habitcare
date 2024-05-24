package io.habitcare.web.mapper;

import io.habitcare.web.dto.CheckDto;
import io.habitcare.web.model.Check;

public class CheckMapper {
    public static Check mapFromCheckDto(CheckDto checkDto) {
        return Check.builder()
                .id(checkDto.getId())
                .checkDate(checkDto.getCheckDate())
                .build();
    }

    public static CheckDto mapToCheckDto(Check check) {
        return CheckDto.builder()
                .id(check.getId())
                .checkDate(check.getCheckDate())
                .build();
    }
}