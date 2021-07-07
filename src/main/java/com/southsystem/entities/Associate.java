package com.southsystem.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Associate implements Serializable {
	private static final long serialVersionUID = 7948129903599004529L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String cpf;
}
