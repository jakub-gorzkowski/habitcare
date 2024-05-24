package io.habitcare.web.controller;

import io.habitcare.web.service.friendship.FriendshipService;
import io.habitcare.web.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.habitcare.web.service.jwt.JwtService;



@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {
    private final FriendshipService friendshipService;
    private final UserService userService;
    private final JwtService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(FriendshipController.class);

    public FriendshipController(FriendshipService friendshipService, UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
        this.jwtService = jwtService;
    }

    @PostMapping("/invite/{receiverId}")
    public ResponseEntity sendInvite(@RequestHeader("Authorization") String token, @PathVariable Long receiverId) {
        String email = jwtService.getEmailFromToken(token);
        Long senderId = userService.getUserIdByEmail(email);

        if (!userService.exists(senderId) || !userService.exists(receiverId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (friendshipService.existsFriendshipByUsers(senderId, receiverId)) {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

        friendshipService.sendInvite(senderId, receiverId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/accept/{senderId}")
    public ResponseEntity acceptInvite(@PathVariable Long senderId, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long receiverId = userService.getUserIdByEmail(email);

        if (!friendshipService.existsFriendshipByUsers(senderId, receiverId)) {
            logger.error("Friendship does not exist between senderId: {} and receiverId: {}", senderId, receiverId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        friendshipService.acceptInvite(senderId, receiverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/decline/{senderId}")
    public ResponseEntity declineInvite(@PathVariable Long senderId, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long receiverId = userService.getUserIdByEmail(email);

        if (friendshipService.getFriendshipStatus(senderId, receiverId).equals("ACCEPTED")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        friendshipService.deleteFriendship(senderId, receiverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-invitation/{receiverId}")
    public ResponseEntity deleteFriendship(@RequestHeader("Authorization") String token, @PathVariable Long receiverId) {
        String email = jwtService.getEmailFromToken(token);
        Long senderId = userService.getUserIdByEmail(email);

        if (!friendshipService.existsFriendshipByUsers(senderId, receiverId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        friendshipService.deleteFriendship(senderId, receiverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}