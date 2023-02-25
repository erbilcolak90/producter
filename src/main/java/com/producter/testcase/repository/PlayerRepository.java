package com.producter.testcase.repository;

import com.producter.testcase.entities.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM players WHERE players.team_id = :teamId")
    Page<Player> findPlayersByTeamId(@Param("teamId") String teamId, Pageable pageable);

}
