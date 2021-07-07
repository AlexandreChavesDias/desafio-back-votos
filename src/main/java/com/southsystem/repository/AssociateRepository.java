package com.southsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.southsystem.entities.Associate;


@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {

    Optional<Associate> findByCpf(String cpf);
}