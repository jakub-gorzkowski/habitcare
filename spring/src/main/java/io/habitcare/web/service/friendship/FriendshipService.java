package io.habitcare.web.service.friendship;

import io.habitcare.web.dto.FriendshipDto;

public interface FriendshipService {
    boolean exists(Long FriendshipId);
    boolean existsFriendshipByUsers(Long senderId, Long receiverId);
    String getFriendshipStatus(Long senderId, Long receiverId);
    FriendshipDto sendInvite(Long senderId, Long receiverId);
    FriendshipDto acceptInvite(Long senderId, Long receiverId);
    void deleteFriendship(Long senderId, Long receiverId);
}