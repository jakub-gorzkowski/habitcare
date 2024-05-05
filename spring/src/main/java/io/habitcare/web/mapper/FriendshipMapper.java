package io.habitcare.web.mapper;

import io.habitcare.web.dto.FriendshipDto;
import io.habitcare.web.model.Friendship;
import io.habitcare.web.model.FriendshipStatus;

public class FriendshipMapper {
    public static Friendship mapFromFriendshipDto(FriendshipDto friendshipDto) {
        return Friendship.builder()
                .id(friendshipDto.getId())
                .requestDate(friendshipDto.getRequestDate())
                .updateDate(friendshipDto.getUpdateDate())
                .status(FriendshipStatus.valueOf(friendshipDto.getStatus().name()))
                .sender(UserMapper.mapFromUserDto(friendshipDto.getSender()))
                .build();
    }

    public static FriendshipDto mapToFriendshipDto(Friendship friendship) {
        return FriendshipDto.builder()
                .id(friendship.getId())
                .requestDate(friendship.getRequestDate())
                .updateDate(friendship.getUpdateDate())
                .status(friendship.getStatus())
                .sender(UserMapper.mapToUserDto(friendship.getSender()))
                .build();
    }
}