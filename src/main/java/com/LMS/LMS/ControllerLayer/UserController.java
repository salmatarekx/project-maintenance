package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.DTO.UserRegistration;
import com.LMS.LMS.ServiceLayer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
public class UserController {
    private final UserService userService ;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/Sign-up")
    public ResponseEntity<String>Sign_up(@RequestBody UserRegistration userReq){
        userService.Register(userReq);

        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered successfully.") ;
    }
}

