package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.DTO.LoginReq;
import com.LMS.LMS.DTO.UserRegistration;
import com.LMS.LMS.ModelLayer.Role;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.ServiceLayer.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(@Lazy UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserRegistration userReq) {
        userReq.setRole(Role.Student); // Assign default role
        userService.Register(userReq);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginReq req) {
        userService.Login(req);
        return ResponseEntity.ok("Login successful.");
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<User> viewProfile(@PathVariable Long id) {
        User user = userService.ViewProfile(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<User> updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody UserRegistration userRegistration) {
        User updatedUser = userService.UpdateProfile(userId, userRegistration);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }
}
