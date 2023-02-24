package com.productuer.testcase.repository;

import com.productuer.testcase.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM roles r WHERE r.name = :roleName")
    Optional<Role> findByName(String roleName);
}
