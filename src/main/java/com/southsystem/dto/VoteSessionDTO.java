package com.southsystem.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteSessionDTO {

	private Long id;
	private ScheduleDTO schedule;
	private LocalDateTime endTime;
	private LocalDateTime startTime;
	private Integer duration;
}
