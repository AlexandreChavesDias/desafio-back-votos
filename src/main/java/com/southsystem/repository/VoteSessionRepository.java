package com.southsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.southsystem.entities.VoteSession;

@Repository
public interface VoteSessionRepository extends JpaRepository<VoteSession, Long> {
}
