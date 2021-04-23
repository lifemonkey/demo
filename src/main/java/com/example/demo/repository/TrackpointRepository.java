package com.example.demo.repository;

import com.example.demo.domain.Trackpoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackpointRepository extends JpaRepository<Trackpoint, Long> {
}
