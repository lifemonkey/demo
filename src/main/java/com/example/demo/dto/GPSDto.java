package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

public class GPSDto implements Serializable {

    static final long serialVersionUID = 1L;

    private Long id;
    private String version;
    private String creator;
    private MetadataDto metadata;
    private List<WaypointDto> waypoints;
    private List<TrackpointDto> trackpoints;

    public GPSDto() {
    }

    public GPSDto(Long id, String version, String creator, MetadataDto metadata, List<WaypointDto> waypoints, List<TrackpointDto> trackpoints) {
        this.id = id;
        this.version = version;
        this.creator = creator;
        this.metadata = metadata;
        this.waypoints = waypoints;
        this.trackpoints = trackpoints;
    }

    public GPSDto withId(Long id) {
        this.id = id;
        return this;
    }

    public GPSDto withVersion(String version) {
        this.version = version;
        return this;
    }

    public GPSDto withCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public GPSDto withMetadata(MetadataDto metadata) {
        this.metadata = metadata;
        return this;
    }

    public GPSDto withWaypoints(List<WaypointDto> waypoints) {
        this.waypoints = waypoints;
        return this;
    }

    public GPSDto withTrackpoints(List<TrackpointDto> trackpoints) {
        this.trackpoints = trackpoints;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public MetadataDto getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataDto metadata) {
        this.metadata = metadata;
    }

    public List<WaypointDto> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<WaypointDto> waypoints) {
        this.waypoints = waypoints;
    }

    public List<TrackpointDto> getTrackpoints() {
        return trackpoints;
    }

    public void setTrackpoints(List<TrackpointDto> trackpoints) {
        this.trackpoints = trackpoints;
    }

    @Override
    public String toString() {
        return "GPSDto{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", creator='" + creator + '\'' +
                ", metadata=" + metadata +
                ", waypoints=" + waypoints +
                ", trackpoints=" + trackpoints +
                '}';
    }
}
