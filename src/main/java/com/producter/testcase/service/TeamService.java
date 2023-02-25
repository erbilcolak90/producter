package com.producter.testcase.service;

import com.producter.testcase.entities.Team;
import com.producter.testcase.inputs.PaginationInput;
import com.producter.testcase.inputs.TeamInput;
import com.producter.testcase.inputs.UpdateTeamInput;
import com.producter.testcase.repository.TeamRepository;
import com.producter.testcase.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
public class TeamService {

    private TeamRepository teamRepository;

    private Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Result<Team> getTeamById(String teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        if (team != null) {
            return new Result<>("Success", true, team);
        } else {
            return new Result<>("Failure", false, null);
        }
    }

    public Page<Team> getAllTeams(PaginationInput paginationInput) {
        Pageable pageable = PageRequest.of(paginationInput.getPage(), paginationInput.getSize(), Sort.by(Sort.Direction.valueOf(paginationInput.getSortBy().toString()), paginationInput.getFieldName()));
        return teamRepository.findAll(pageable);
    }

    @Transactional
    public Result<Team> createTeam(TeamInput teamInput) {
        Team team = new Team();

        Team existTeam = teamRepository.findByName(teamInput.getName());
        if (existTeam != null) {
            logger.error("team not created");
            return new Result<>("Team name is already exist", false, null);
        } else {
            team.setName(teamInput.getName());
            team.setCapacity(teamInput.getCapacity());
            team.setId(UUID.randomUUID().toString());
            OffsetDateTime offsetDateTime = OffsetDateTime.now();
            team.setCreatedDate(offsetDateTime);
            team.setUpdateDate(offsetDateTime);
            team.setDeleted(false);

            teamRepository.save(team);

            logger.info("Team created by Id : " + SecurityContextHolder.getContext().getAuthentication().getName());

            return new Result<>("Success", true, team);
        }


    }

    @Transactional
    public Result<Team> updateTeam(UpdateTeamInput updateTeamInput) {
        Team team = teamRepository.findById(updateTeamInput.getId()).orElse(null);
        if (team == null) {
            return new Result<>("Team not found", false, null);
        }

        Team existTeam = teamRepository.findByName(updateTeamInput.getName());
        if (existTeam != null) {
            return new Result<>("Team is already exist", false, null);
        }
        if (updateTeamInput.getName() != null || !updateTeamInput.getName().isEmpty()) {

            team.setName(updateTeamInput.getName());
        }
        if (updateTeamInput.getCapacity() != 0) {
            team.setCapacity(updateTeamInput.getCapacity());
        }
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        team.setUpdateDate(offsetDateTime);

        teamRepository.save(team);

        logger.info("Team updated by Id : " + SecurityContextHolder.getContext().getAuthentication().getName());


        return new Result<>("Success", true, team);
    }

    @Transactional
    public Result<String> deleteTeamById(String teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        if (team == null) {
            return new Result<>("Team not found", false, null);
        }
        team.setDeleted(true);
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        team.setUpdateDate(offsetDateTime);

        teamRepository.save(team);

        return new Result<>("Success", true, null);
    }
}
