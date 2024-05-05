package io.habitcare.web.controller;

import io.habitcare.web.dto.FriendshipDto;
import io.habitcare.web.dto.UserDto;
import io.habitcare.web.service.friendship.FriendshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @GetMapping("/friends/{userId}")
    public ResponseEntity<List<UserDto>> getAllFriends(@PathVariable Long userId) {
        List<UserDto> friends = friendshipService.getAllFriends(userId);
        return ResponseEntity.ok(friends);
    }

    @PostMapping("/invite")
    public ResponseEntity<FriendshipDto> sendInvite(@RequestParam Long senderId, @RequestParam Long receiverId) {
        FriendshipDto friendshipDto = friendshipService.sendInvite(senderId, receiverId);
        return ResponseEntity.ok(friendshipDto);
    }

    @PatchMapping("/accept/{id}")
    public ResponseEntity<FriendshipDto> acceptInvite(@PathVariable Long id) {
        FriendshipDto friendshipDto = friendshipService.acceptInvite(id);
        return ResponseEntity.ok(friendshipDto);
    }

    @PatchMapping("/decline/{id}")
    public ResponseEntity<FriendshipDto> declineInvite(@PathVariable Long id) {
        FriendshipDto friendshipDto = friendshipService.declineInvite(id);
        return ResponseEntity.ok(friendshipDto);
    }

    @PatchMapping("/block/{id}")
    public ResponseEntity<FriendshipDto> blockFriend(@PathVariable Long id) {
        FriendshipDto friendshipDto = friendshipService.blockFriend(id);
        return ResponseEntity.ok(friendshipDto);
    }
}