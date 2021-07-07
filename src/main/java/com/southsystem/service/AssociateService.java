package com.southsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.southsystem.dto.AssociateDTO;
import com.southsystem.dto.UserStatusDTO;
import com.southsystem.entities.Associate;
import com.southsystem.enums.UserStatus;
import com.southsystem.exceptions.ScheduleException;
import com.southsystem.repository.AssociateRepository;

@Service
//@RequiredArgsConstructor
public class AssociateService {

	@Autowired
	private ModelMapper modelMapper;

	@Value("${url.cpf}")
	private String URL_USER_CPF;
	
	@Autowired
	private AssociateRepository associateRepository;

	public AssociateDTO findById(Long id) {
		Associate associate = associateRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Associate not found"));

		return modelMapper.map(associate, AssociateDTO.class);
	}

	public AssociateDTO findByCpf(String cpf) {
		Associate associate = associateRepository.findByCpf(cpf)
				.orElseThrow(() -> new EntityNotFoundException("Associate not found"));

		return modelMapper.map(associate, AssociateDTO.class);
	}

	public AssociateDTO createAssociate(AssociateDTO associateDTO) {
		associateRepository.save(modelMapper.map(associateDTO, Associate.class));
		return associateDTO;
	}

	public List<AssociateDTO> listAssociates() {
		return associateRepository.findAll().stream().map(associate -> modelMapper.map(associate, AssociateDTO.class))
				.collect(Collectors.toList());
	}

	public UserStatus validatesAssociatedCpf(AssociateDTO associateDTO) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<UserStatusDTO> userStatus = restTemplate.getForEntity(URL_USER_CPF + associateDTO.getCpf(),
					UserStatusDTO.class);
			return UserStatus.valueOf(userStatus.getBody().getStatus());
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				throw new ScheduleException("Associated with Invalid CPF");
			} else {
				e.printStackTrace();
				throw new ScheduleException("Error validating CPF");
			}
		}
	}
}