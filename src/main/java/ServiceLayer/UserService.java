package ServiceLayer;

import DTO.UserRegistration;
import ModelLayer.User;
import RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
