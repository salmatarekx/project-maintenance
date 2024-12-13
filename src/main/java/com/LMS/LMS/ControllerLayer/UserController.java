//package com.LMS.LMS.ControllerLayer;
//
//import com.LMS.LMS.DTO.LoginReq;
//import com.LMS.LMS.DTO.UserRegistration;
//import com.LMS.LMS.ModelLayer.Role;
//import com.LMS.LMS.ServiceLayer.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/Users")
//public class UserController {
//    @Autowired
//    private  UserService userService ;
//
//
//    @PostMapping("/Sign-up")
//    public ResponseEntity<String>Sign_up(@RequestBody UserRegistration userReq){
//        userReq.setRole(Role.Student);
//        userService.Register(userReq);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered successfully.") ;
//    }
//    @PostMapping("/Login")
//    public ResponseEntity<String>Login(@RequestBody LoginReq req){
//        userService.Login(req);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Login Successfully. ") ;
//    }
//}
//
