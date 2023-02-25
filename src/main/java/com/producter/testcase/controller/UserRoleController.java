package com.producter.testcase.controller;

import com.producter.testcase.result.Result;
import com.producter.testcase.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserRoleController {

    private UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<String> getUserRolesByUserId(@Argument String userId) {
        return userRoleService.getUserRolesByUserIds(userId);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result<String> addRoleToUser(@Argument String userId, @Argument String roleName) {
        Result<String> result = userRoleService.addRoleToUser(userId, roleName);
        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());
    }
}
