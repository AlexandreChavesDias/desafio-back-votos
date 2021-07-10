package com.southsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssociateDTO {

	private Long id;
	private String cpf;

	public AssociateDTO(String cpf) {
		this.cpf =cpf;
	}
}
