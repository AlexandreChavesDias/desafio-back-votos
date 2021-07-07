package com.southsystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.dto.AssociateDTO;
import com.southsystem.dto.ScheduleDTO;
import com.southsystem.dto.VoteDTO;
import com.southsystem.entities.Schedule;
import com.southsystem.entities.VoteSession;
import com.southsystem.enums.TypeVote;
import com.southsystem.enums.UserStatus;
import com.southsystem.exceptions.ScheduleException;
import com.southsystem.repository.ScheduleRepository;

@Service
public class ScheduleService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private AssociateService associateService;

	public ScheduleDTO criateSchedule(ScheduleDTO scheduleDTO) {
		Schedule schedule = scheduleRepository.save(modelMapper.map(scheduleDTO, Schedule.class));
		return modelMapper.map(schedule, ScheduleDTO.class);
	}

	public ScheduleDTO findById(Long id) {
		Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("S"));
		return modelMapper.map(schedule, ScheduleDTO.class);
	}

	public VoteDTO voteSchedule(VoteDTO voteDTO) {
		ScheduleDTO scheduleDTO = this.findById(voteDTO.getSchedule());

		validateSessionSchedule(scheduleDTO.getVoteSession());

		validateScheduleVotes(scheduleDTO.getVotes(), voteDTO.getCpf());

		VoteAccounting(scheduleDTO, voteDTO.getVote());

		scheduleRepository.save(modelMapper.map(scheduleDTO, Schedule.class));

		return voteDTO;
	}

	private void VoteAccounting(ScheduleDTO scheduleDTO, String vote) {
		if (TypeVote.YES.getTypeVote().equals(vote)) {
			scheduleDTO.setYesVote(scheduleDTO.getYesVote() + 1);
		} else if (TypeVote.NO.getTypeVote().equals(vote)) {
			scheduleDTO.setNoVote(scheduleDTO.getNoVote() + 1);
		} else {
			throw new ScheduleException("Invalid vote");
		}
	}

	private void validateScheduleVotes(List<AssociateDTO> votes, String cpfAssociate) {
		if (Objects.nonNull(votes) && !votes.isEmpty()
				&& votes.stream().map(AssociateDTO::getCpf).anyMatch(cpf -> cpf.equals(cpfAssociate))) {
			throw new ScheduleException("Associado já votou");
		} else {
			AssociateDTO associateDTO = associateService.findByCpf(cpfAssociate);
			validateAssociate(associateDTO);
			votes.add(associateDTO);
		}
	}

	private void validateSessionSchedule(VoteSession voteSession) {
		if (Objects.isNull(voteSession)) {
			throw new ScheduleException("Schedule has no voting session");
		}

		if (!voteSession.getEndTime().isAfter(LocalDateTime.now())) {
			throw new ScheduleException("Schedule session finished");
		}
	}

	public String scheduleResult(Long id) {
		ScheduleDTO scheduleDTO = this.findById(id);
		return scheduleDTO.getYesVote() > scheduleDTO.getNoVote() ? TypeVote.YES.getTypeVote()
				: TypeVote.NO.getTypeVote();
	}

	private void validateAssociate(AssociateDTO associateDTO) {
		UserStatus userStatus = associateService.validatesAssociatedCpf(associateDTO);
		if (UserStatus.UNABLE_TO_VOTE.getStatus().equals(userStatus.getStatus())) {
			throw new ScheduleException("Member cannot vote");
		}
	}

}