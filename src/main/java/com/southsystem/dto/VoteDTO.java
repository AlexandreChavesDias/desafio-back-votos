package com.southsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteDTO {

    private String cpf;
    private String vote;
    private Long schedule;
}