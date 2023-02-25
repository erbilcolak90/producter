package com.producter.testcase.repository;

import com.producter.testcase.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    @Query(nativeQuery = true, value = "SELECT ur.role_id FROM user_roles ur WHERE ur.user_id= :userId")
    List<String> findByRoleId(@Param("userId") String userId);
}
