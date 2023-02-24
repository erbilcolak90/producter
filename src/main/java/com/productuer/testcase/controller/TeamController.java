package com.productuer.testcase.controller;

import com.productuer.testcase.entities.Team;
import com.productuer.testcase.inputs.PaginationInput;
import com.productuer.testcase.inputs.TeamInput;
import com.productuer.testcase.inputs.UpdateTeamInput;
import com.productuer.testcase.result.Result;
import com.productuer.testcase.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @QueryMapping
    public Result<Team> getTeamById(@Argument String teamId){
        Result<Team> result = teamService.getTeamById(teamId);
        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Team> getAllTeams(@Argument PaginationInput paginationInput){
        return teamService.getAllTeams(paginationInput);
    }

    @MutationMapping
    public Result<Team> createTeam(@Argument TeamInput teamInput){
        Result<Team> result = teamService.createTeam(teamInput);
        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());
    }

    @MutationMapping
    public Result<Team> updateTeam(@Argument UpdateTeamInput updateTeamInput){
        Result<Team> result = teamService.updateTeam(updateTeamInput);
        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());
    }

    @MutationMapping
    public Result<String> deleteTeamById(@Argument String teamId){
        Result<String> result = teamService.deleteTeamById(teamId);
        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());
    }


}
