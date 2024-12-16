package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.DTO.LoginReq;
import com.LMS.LMS.DTO.UserRegistration;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(@Lazy UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public User Register(UserRegistration userReq) {
        User existedUser  = userRepository.findByEmail(userReq.getEmail()).orElse(null) ;


        if (existedUser != null){
            throw new RuntimeException("Email is already Exist !");
        }
        User user = new User();
        user.setUserName(userReq.getUsername());
        user.setEmail(userReq.getEmail());
        user.setPassword(userReq.getPassword());
        user.setRole(userReq.getRole());

        return userRepository.save(user);

    }

    public User Login(LoginReq req) {
        User user = userRepository.findByEmail(req.GetEmail()).orElseThrow(() -> new RuntimeException("Invalid Email"));
        if (!user.getPassword().equals(req.getPassword())) {
            throw new RuntimeException("INVALID Password"); // ###Problem
        }
        return user ;
    }
    public User ViewProfile(long Id){
        User user = userRepository.findById(Id).orElse(null);
        if (user != null){
            return user ;
        }
        else {
            throw new RuntimeException("Profile not found!");
        }
    }
    public User UpdateProfile(Long id,UserRegistration userRegistration){
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new RuntimeException("Profile not found!");
        }
        if (userRegistration.getPassword() != null && !userRegistration.getPassword().isEmpty() && !userRegistration.getUsername().isEmpty() && userRegistration.getRole()!=null && !userRegistration.getEmail().isEmpty()) {
            user.setPassword(userRegistration.getPassword());
            user.setUserName(userRegistration.getUsername());
            user.setEmail(userRegistration.getEmail());
            user.setRole(userRegistration.getRole());
            user.setPassword(userRegistration.getPassword());
        }
        return userRepository.save(user);
    }



}
