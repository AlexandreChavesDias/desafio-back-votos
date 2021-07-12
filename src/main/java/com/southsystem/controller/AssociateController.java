package com.southsystem.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.dto.AssociateDTO;
import com.southsystem.exceptions.NotFoundException;
import com.southsystem.service.AssociateService;

@RestController
@RequestMapping("api/v1/associates")
public class AssociateController {

	@Autowired
	private AssociateService associateService;

	@PostMapping
	public ResponseEntity<AssociateDTO> createAssociate(@RequestBody @Valid AssociateDTO associateDTO)
			throws NotFoundException, URISyntaxException {
		return new ResponseEntity<>(associateService.createAssociate(associateDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<AssociateDTO>> listAssociates() throws NotFoundException {
		return ResponseEntity.ok(associateService.listAssociates());
	}

}