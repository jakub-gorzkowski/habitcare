package io.habitcare.web.service.friendship;

import io.habitcare.web.dto.FriendshipDto;
import io.habitcare.web.dto.UserDto;
import io.habitcare.web.model.Friendship;
import io.habitcare.web.model.User;

import java.util.List;

public interface FriendshipService {
    List<UserDto> getAllFriends(Long userId);
    FriendshipDto sendInvite(Long senderId, Long receiverId);
    FriendshipDto acceptInvite(Long FriendshipId);
    FriendshipDto declineInvite(Long FriendshipId);
    FriendshipDto blockFriend(Long FriendshipId);
}