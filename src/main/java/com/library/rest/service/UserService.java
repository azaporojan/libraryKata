package com.library.rest.service;

import com.library.persistence.entity.User;
import com.library.persistence.repository.UserRepository;
import com.library.rest.advice.exceptions.LibraryManagementException;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        userRepository.save(user);

        User user1 = new User();
        user1.setFirstName("Jane");
        user1.setLastName("Doe");
        userRepository.save(user1);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new LibraryManagementException("User not found"));
    }

}