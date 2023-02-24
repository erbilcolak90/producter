package com.productuer.testcase.controller;

import com.productuer.testcase.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getRoleIdByRoleName(@Argument String roleName) {
        return roleService.getRoleIdByRoleName(roleName);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createRole(@Argument String roleName) {
        return roleService.createRole(roleName);
    }
}
