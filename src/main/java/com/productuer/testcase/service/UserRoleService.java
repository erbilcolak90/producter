package com.productuer.testcase.service;

import com.productuer.testcase.entities.User;
import com.productuer.testcase.entities.UserRole;
import com.productuer.testcase.repository.UserRoleRepository;
import com.productuer.testcase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserRoleService {

    private UserRoleRepository userRoleRepository;
    private CustomUserService customUserService;
    private RoleService roleService;

    @Autowired
    @Lazy
    public UserRoleService(UserRoleRepository userRoleRepository, CustomUserService customUserService, RoleService roleService) {
        this.userRoleRepository = userRoleRepository;
        this.customUserService = customUserService;
        this.roleService = roleService;
    }

    public List<String> getUserRolesByUserIds(String userId) {
        return userRoleRepository.findByRoleId(userId);
    }

    @Transactional
    public Result<String> addRoleToUser(String userId, String roleName) {
        User user = customUserService.findByUserId(userId).getResult();
        String roleId = roleService.getRoleIdByRoleName(roleName);
        if (user != null && roleId != null) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRole.setId(UUID.randomUUID().toString());
            userRoleRepository.save(userRole);
            return new Result<>("Success", true, roleName);
        } else {
            return new Result<>("Failure", false, null);
        }
    }

}
