package com.southsystem.dto;

import java.util.List;

import com.southsystem.entities.VoteSession;
import com.southsystem.enums.TypeVote;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleDTO {
    private Long id;
    private String subject;
    private int yesVote;
    private int noVote;
    private TypeVote result;
    private List<AssociateDTO> votes;
    private VoteSession voteSession;
}