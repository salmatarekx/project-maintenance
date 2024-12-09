package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.DTO.UserRegistration;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository ;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User Register(UserRegistration userReq){
       User user = new User();
       user.setUserName(userReq.getUsername());
       user.setEmail(userReq.getEmail());
       user.setPassword(userReq.getPassword());
       user.setRole(userReq.getRole());
       return userRepository.save(user) ;


    }

}
