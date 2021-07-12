package com.southsystem.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.dto.VoteDTO;
import com.southsystem.exceptions.NotFoundException;
import com.southsystem.service.ScheduleService;

@RestController
@RequestMapping(value = "api/v1/vote")
public class VoteController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping
	public ResponseEntity<VoteDTO> voteSchedule(@RequestBody VoteDTO voteDTO) throws NotFoundException, URISyntaxException {
		return new ResponseEntity<>(scheduleService.voteSchedule(voteDTO),HttpStatus.CREATED);
	}
}
