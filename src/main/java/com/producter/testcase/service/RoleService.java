package com.producter.testcase.service;

import com.producter.testcase.entities.Role;
import com.producter.testcase.repository.RoleRepository;
import com.producter.testcase.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Result<String> createRole(String roleName) {
        Role role = new Role();

        Role existRole = roleRepository.findById(roleName).orElse(null);
        if (existRole != null) {
            return new Result<>("Failure",false, "Role is already exist");
        }
        role.setId(UUID.randomUUID().toString());
        role.setName(roleName);
        roleRepository.save(role);

        logger.info("Role created by Id:  " + SecurityContextHolder.getContext().getAuthentication().getName());

        return new Result<>("Success", true, role.getId());
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
