package com.productuer.testcase.service;

import com.productuer.testcase.entities.Role;
import com.productuer.testcase.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RoleService {

    private RoleRepository roleRepository;

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
