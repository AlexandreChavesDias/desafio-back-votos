package com.southsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.dto.AssociateDTO;
import com.southsystem.service.AssociateService;

@RestController
@RequestMapping("api/v1/associates")
public class AssociateController {

	@Autowired
	private AssociateService associateService;

	@PostMapping
	public ResponseEntity<AssociateDTO> createAssociate(@RequestBody AssociateDTO associateDTO) {
		return ResponseEntity.ok(associateService.createAssociate(associateDTO));
	}

	@GetMapping//funcionou
	public ResponseEntity<List<AssociateDTO>> listAssociates() {
		return ResponseEntity.ok(associateService.listAssociates());
	}

}