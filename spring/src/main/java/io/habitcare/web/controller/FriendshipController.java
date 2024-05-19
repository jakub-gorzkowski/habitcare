package io.habitcare.web.controller;

import io.habitcare.web.service.friendship.FriendshipService;
import io.habitcare.web.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {
    private final FriendshipService friendshipService;
    private final UserService userService;

    public FriendshipController(FriendshipService friendshipService, UserService userService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
    }

    @PostMapping("/invite")
    public ResponseEntity sendInvite(@RequestParam Long senderId, @RequestParam Long receiverId) {
        if (!userService.exists(senderId) || !userService.exists(receiverId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (friendshipService.existsFriendshipByUsers(receiverId, senderId)) {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

        friendshipService.sendInvite(senderId, receiverId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/accept/{id}")
    public ResponseEntity acceptInvite(@PathVariable Long id) {
        if (!friendshipService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        friendshipService.acceptInvite(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/decline/{id}")
    public ResponseEntity declineInvite(@PathVariable Long id) {
        if (friendshipService.getFriendshipStatus(id).equals("ACCEPTED")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        friendshipService.deleteFriendship(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFriendship(@PathVariable Long id) {
        friendshipService.deleteFriendship(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}