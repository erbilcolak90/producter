package com.productuer.testcase.repository;

import com.productuer.testcase.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    Team findByName(String teamName);
}
