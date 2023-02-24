package com.productuer.testcase.service;

import com.productuer.testcase.entities.Player;
import com.productuer.testcase.entities.Team;
import com.productuer.testcase.inputs.PaginationInput;
import com.productuer.testcase.inputs.PlayerInput;
import com.productuer.testcase.inputs.UpdatePlayerInput;
import com.productuer.testcase.repository.PlayerRepository;
import com.productuer.testcase.repository.TeamRepository;
import com.productuer.testcase.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

import java.util.UUID;

@Slf4j
@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private TeamService teamService;
    private TeamRepository teamRepository;

    private Logger logger = LoggerFactory.getLogger(PlayerService.class);

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamService teamService, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
        this.teamRepository = teamRepository;
    }

    public Result<Player> getPlayerById(String playerId) {
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player != null) {
            return new Result<>("Success", true, player);
        } else {
            return new Result<>("Failure", false, null);
        }
    }

    public Page<Player> getAllPlayers(PaginationInput paginationInput) {
        Pageable pageable = PageRequest.of(paginationInput.getPage(), paginationInput.getSize(), Sort.by(Sort.Direction.valueOf(paginationInput.getSortBy().toString()), paginationInput.getFieldName()));

        return playerRepository.findAll(pageable);
    }

    public Page<Player> getPlayersByTeamId(PaginationInput paginationInput, String teamId) {
        Pageable pageable = PageRequest.of(paginationInput.getPage(), paginationInput.getSize(), Sort.by(Sort.Direction.valueOf(paginationInput.getSortBy().toString()), paginationInput.getFieldName()));

        return playerRepository.findPlayersByTeamId(teamId, pageable);

    }

    @Transactional
    public Result<Player> createPlayer(PlayerInput playerInput) {
        logger.info("create player method triggered");

        Player player = new Player();
        Page<Player> playerList = playerRepository.findPlayersByTeamId(playerInput.getTeamId(), Pageable.unpaged());
        Team team = teamRepository.findById(playerInput.getTeamId()).orElse(null);
        if (team != null && playerList.getSize() < team.getCapacity()) {
            player.setName(playerInput.getName());
            player.setSurname(playerInput.getSurname());
            player.setPosition(playerInput.getPosition());
            player.setTeamId(playerInput.getTeamId());
            player.setDeleted(false);
            OffsetDateTime offsetDateTime = OffsetDateTime.now();
            player.setCreatedDate(offsetDateTime);
            player.setUpdateDate(offsetDateTime);
            player.setId(UUID.randomUUID().toString());
            playerRepository.save(player);

            logger.info("user created");

            return new Result<>("Success", true, player);

        } else {
            return new Result<>("Team size is full", false, null);
        }
    }

    @Transactional
    public Result<Player> updatePlayer(UpdatePlayerInput updatePlayerInput) {
        Player player = playerRepository.findById(updatePlayerInput.getId()).orElse(null);
        if (player != null) {
            if (updatePlayerInput.getName() != null || !updatePlayerInput.getName().isEmpty()) {
                player.setName(updatePlayerInput.getName());
            }
            if (updatePlayerInput.getSurname() != null || !updatePlayerInput.getSurname().isEmpty()) {
                player.setSurname(updatePlayerInput.getSurname());
            }
            if (updatePlayerInput.getPosition() != null) {
                player.setPosition(updatePlayerInput.getPosition());
            }
            OffsetDateTime offsetDateTime = OffsetDateTime.now();
            player.setUpdateDate(offsetDateTime);

            playerRepository.save(player);

            logger.info("player updated");

            return new Result<>("Success", true, player);
        } else {
            return new Result<>("Failure", false, null);
        }

    }

    @Transactional
    public Result<Player> updatePlayerTeam(String playerId, String teamId) {
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player == null) {
            return new Result<>("Player not found", false, null);
        }
        Team team = teamService.getTeamById(teamId).getResult();
        if (team == null) {
            return new Result<>("Team not found", false, null);
        }
        player.setTeamId(teamId);
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        player.setUpdateDate(offsetDateTime);

        playerRepository.save(player);

        logger.info("player team is updated");

        return new Result<>("Success", true, player);
    }

    @Transactional
    public Result<String> deletePlayerById(String playerId) {
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player != null) {
            player.setDeleted(true);
            OffsetDateTime offsetDateTime = OffsetDateTime.now();
            player.setUpdateDate(offsetDateTime);
            playerRepository.save(player);

            logger.info(playerId+" User deleted");

            return new Result<>("Player deleted", true, null);

        } else {
            return new Result<>("Player not found", false, null);
        }
    }
}
