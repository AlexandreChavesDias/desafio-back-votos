package com.southsystem.service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.dto.ScheduleDTO;
import com.southsystem.dto.VoteSessionDTO;
import com.southsystem.entities.VoteSession;
import com.southsystem.exceptions.ScheduleException;
import com.southsystem.repository.VoteSessionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoteSessionService {
    @Autowired
    private  ModelMapper modelMapper;
    private final VoteSessionRepository voteSessionRepository;
    private final ScheduleService ScheduleService;

    public VoteSessionDTO createVotingSession(VoteSessionDTO voteSessionDTO) {
        if (Objects.isNull(voteSessionDTO.getStartTime())) {
        	voteSessionDTO.setStartTime(LocalDateTime.now());
        }

        if (Objects.isNull(voteSessionDTO.getDuration())) voteSessionDTO.setDuration(1);;

        ScheduleDTO scheduleDTO = ScheduleService.findById(voteSessionDTO.getSchedule().getId());

        if (Objects.nonNull(scheduleDTO.getVoteSession())) {
            throw new ScheduleException("Schedule already passed session");
        }
        
        voteSessionDTO.setEndTime(voteSessionDTO.getStartTime().plusMinutes(voteSessionDTO.getDuration()));
        voteSessionDTO.setSchedule(scheduleDTO);
        voteSessionRepository.save(modelMapper.map(voteSessionDTO, VoteSession.class));

        return modelMapper.map(voteSessionDTO, VoteSessionDTO.class);
    }
}
