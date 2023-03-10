package com.producter.testcase.controller;

import com.producter.testcase.entities.User;
import com.producter.testcase.result.Result;
import com.producter.testcase.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private CustomUserService customUserService;

    @Autowired
    public UserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @QueryMapping
    public Result<User> getUserById(@Argument String userId) {
        Result<User> result = customUserService.findByUserId(userId);

        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());
    }

}
