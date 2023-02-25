package com.producter.testcase.service;

import com.producter.testcase.entities.User;
import com.producter.testcase.entities.UserRole;
import com.producter.testcase.repository.UserRoleRepository;
import com.producter.testcase.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserRoleService {

    private UserRoleRepository userRoleRepository;
    private CustomUserService customUserService;
    private RoleService roleService;

    private Logger logger = LoggerFactory.getLogger(UserRoleService.class);

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

            logger.info(roleName+" role added to userId : "+userId+" from by Id : " + SecurityContextHolder.getContext().getAuthentication().getName());

            return new Result<>("Success", true, roleName);
        } else {
            return new Result<>("Failure", false, null);
        }
    }

}
