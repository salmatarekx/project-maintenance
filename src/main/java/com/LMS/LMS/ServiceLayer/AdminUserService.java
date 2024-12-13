package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.DTO.UserRegistration;
import com.LMS.LMS.ModelLayer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    @Autowired
    private  UserService userService ;

    public AdminUserService(UserService userService) {
        this.userService = userService;
    }

    public User CreateUser(UserRegistration userRegistration){


        return userService.Register(userRegistration);

    }


}
