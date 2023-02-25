package com.producter.testcase.controller;

import com.producter.testcase.entities.Player;
import com.producter.testcase.inputs.PaginationInput;
import com.producter.testcase.inputs.PlayerInput;
import com.producter.testcase.inputs.UpdatePlayerInput;
import com.producter.testcase.result.Result;
import com.producter.testcase.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;


@Controller
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public Result<Player> getPlayerById(@Argument String playerId) {
        Result<Player> result = playerService.getPlayerById(playerId);

        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Player> getAllPlayers(@Argument @Valid PaginationInput paginationInput) {
        return playerService.getAllPlayers(paginationInput);
    }

    @QueryMapping
    public Page<Player> getPlayersByTeamId(@Argument @Valid PaginationInput paginationInput, @Argument String teamId) {
        return playerService.getPlayersByTeamId(paginationInput, teamId);
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Result<Player> createPlayer(@Argument @Valid PlayerInput playerInput) {
        Result<Player> result = playerService.createPlayer(playerInput);
        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Result<Player> updatePlayer(@Argument @Valid UpdatePlayerInput updatePlayerInput) {
        Result<Player> result = playerService.updatePlayer(updatePlayerInput);
        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());

    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Result<Player> updatePlayerTeam(@Argument @Valid String playerId, @Argument String teamId) {

        Result<Player> result = playerService.updatePlayerTeam(playerId, teamId);
        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Result<String> deletePlayerById(@Argument @Valid String playerId) {
        Result<String> result = playerService.deletePlayerById(playerId);
        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());
    }
}
