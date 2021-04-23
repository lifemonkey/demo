package com.example.demo.repository;

import com.example.demo.domain.TrackSegment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackSegmentRepository extends JpaRepository<TrackSegment, Long> {
}
