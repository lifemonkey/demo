package com.example.demo.dto;

import com.example.demo.domain.Trackpoint;

import java.io.Serializable;
import java.util.List;

public class TrackSegmentDto implements Serializable  {

    static final long serialVersionUID = 1L;

    private Long id;
    private List<Trackpoint> trackpoints;

    public TrackSegmentDto() {
    }

    public TrackSegmentDto(Long id, List<Trackpoint> trackpoints) {
        this.id = id;
        this.trackpoints = trackpoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Trackpoint> getTrackpoints() {
        return trackpoints;
    }

    public void setTrackpoints(List<Trackpoint> trackpoints) {
        this.trackpoints = trackpoints;
    }

    @Override
    public String toString() {
        return "TrackSegmentDto{" +
                "id=" + id +
                ", trackpoints=" + trackpoints +
                '}';
    }
}
