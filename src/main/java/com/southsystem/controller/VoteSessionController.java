package com.southsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.dto.VoteSessionDTO;
import com.southsystem.service.VoteSessionService;

@RestController
@RequestMapping("api/v1/votesession")
public class VoteSessionController {

	@Autowired
	private VoteSessionService voteSessionService;

	@PostMapping
	public ResponseEntity<VoteSessionDTO> criarSessaoVotacao(@RequestBody VoteSessionDTO voteSessionDTO) {
		return ResponseEntity.ok(voteSessionService.createVotingSession(voteSessionDTO));
	}

}
