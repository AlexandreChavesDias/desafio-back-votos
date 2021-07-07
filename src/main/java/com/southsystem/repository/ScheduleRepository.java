package com.southsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.southsystem.entities.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
