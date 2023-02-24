package com.productuer.testcase.service;

import com.productuer.testcase.controller.AuthController;
import com.productuer.testcase.entities.Role;
import com.productuer.testcase.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class RoleService {

    private RoleRepository roleRepository;

    private Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public String createRole(String roleName) {
        Role role = new Role();

        Role existRole = roleRepository.findById(roleName).orElse(null);
        if (existRole != null) {
            return "Role is already exist";
        }
        role.setId(UUID.randomUUID().toString());
        role.setName(roleName);
        roleRepository.save(role);

        logger.info("role created");

        return role.getId();
    }

    @Transactional
    public String getRoleIdByRoleName(String roleName) {
        Role role = roleRepository.findByName(roleName).orElse(null);
        if (role == null) {
            return "role not found";
        } else {
            return role.getId();
        }
    }


}
