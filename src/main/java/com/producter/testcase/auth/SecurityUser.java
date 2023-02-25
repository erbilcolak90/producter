package com.producter.testcase.auth;

import com.producter.testcase.entities.Role;
import com.producter.testcase.entities.User;
import com.producter.testcase.repository.RoleRepository;
import com.producter.testcase.repository.UserRoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private User user;
    private UserRoleRepository userRoleRepository;
    private RoleRepository roleRepository;

    public SecurityUser(User user, UserRoleRepository userRoleRepository, RoleRepository roleRepository) {
        this.user = user;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> roles = new ArrayList<>();
        List<String> roleList = userRoleRepository.findByRoleId(user.getId());


        for(String role : roleList){
            Role db_role = roleRepository.findById(role).orElse(null);
            if(db_role != null){
                roles.add(new SimpleGrantedAuthority("ROLE_"+db_role.getName()));
            }

        }

        return roles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
