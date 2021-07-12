package com.southsystem.controller;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.dto.VoteSessionDTO;
import com.southsystem.exceptions.NotFoundException;
import com.southsystem.service.VoteSessionService;

@RestController
@RequestMapping("api/v1/votesession")
public class VoteSessionController {

	@Autowired
	private VoteSessionService voteSessionService;

	@PostMapping
	public ResponseEntity<VoteSessionDTO> criarSessaoVotacao(@RequestBody @Valid VoteSessionDTO voteSessionDTO)
			throws NotFoundException, URISyntaxException {
		return new ResponseEntity<>(voteSessionService.createVotingSession(voteSessionDTO), HttpStatus.CREATED);
	}

}
