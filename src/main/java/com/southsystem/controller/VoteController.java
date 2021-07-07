package com.southsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.dto.VoteDTO;
import com.southsystem.service.ScheduleService;

@RestController
@RequestMapping(value = "api/v1/vote")
public class VoteController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping
	public ResponseEntity<VoteDTO> voteSchedule(@RequestBody VoteDTO voteDTO) {
		return ResponseEntity.ok(scheduleService.voteSchedule(voteDTO));
	}
}
