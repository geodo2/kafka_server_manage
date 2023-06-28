package com.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FailRepository extends JpaRepository<FailEvent, Long> {
    List<FailEvent> findAllByComplete(boolean complete);
}
