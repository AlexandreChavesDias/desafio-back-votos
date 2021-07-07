package com.southsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.dto.ScheduleDTO;
import com.southsystem.service.ScheduleService;

@RestController
@RequestMapping(value = "api/v1/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping
	public ResponseEntity<ScheduleDTO> criateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
		return ResponseEntity.ok(scheduleService.criateSchedule(scheduleDTO));
	}

	@GetMapping("/{id}") // FUNCIONOU
	public ResponseEntity<String> scheduleResult(@PathVariable Long id) {
		return ResponseEntity.ok(scheduleService.scheduleResult(id));
	}

}
