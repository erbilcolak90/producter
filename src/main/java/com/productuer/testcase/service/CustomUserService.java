package com.productuer.testcase.service;

import com.productuer.testcase.entities.User;
import com.productuer.testcase.inputs.UserInput;
import com.productuer.testcase.repository.UserRepository;
import com.productuer.testcase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class CustomUserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRoleService userRoleService;


    @Autowired
    @Lazy
    public CustomUserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleService = userRoleService;
    }

    // token manager using this method
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Result<User> findByUserId(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return new Result<>("Success", true, user);
        } else {
            return new Result<>("User not found", false, null);
        }

    }

    @Transactional
    public Result<String> signUp(UserInput userInput) {

        User usernameIsExist = userRepository.findByUsername(userInput.getUsername());
        if (usernameIsExist == null || usernameIsExist.getUsername().isEmpty()) {
            User user = new User();

            user.setUsername(userInput.getUsername());
            user.setEmail(userInput.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(userInput.getPassword()));
            user.setId(UUID.randomUUID().toString());
            OffsetDateTime offsetDateTime = OffsetDateTime.now();
            user.setCreatedDate(offsetDateTime);
            user.setUpdateDate(offsetDateTime);
            user.setDeleted(false);
            userRepository.save(user);
            userRoleService.addRoleToUser(user.getId(), "USER");

            return new Result<>("Success", true, user.getId());
        } else {
            return new Result<>("Failure", false, null);
        }
    }

}
