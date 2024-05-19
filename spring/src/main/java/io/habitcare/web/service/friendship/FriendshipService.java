package io.habitcare.web.service.friendship;

import io.habitcare.web.dto.FriendshipDto;

public interface FriendshipService {
    boolean exists(Long FriendshipId);
    boolean existsFriendshipByUsers(Long receiverId, Long senderId);
    String getFriendshipStatus(Long FriendshipId);
    FriendshipDto sendInvite(Long senderId, Long receiverId);
    FriendshipDto acceptInvite(Long FriendshipId);
    void deleteFriendship(Long FriendshipId);
}