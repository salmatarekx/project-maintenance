package com.LMS.LMS.RepositoryLayer;

import com.LMS.LMS.ModelLayer.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   //  Define a custom query method
    Optional<User> findByEmail(String Email);
}
