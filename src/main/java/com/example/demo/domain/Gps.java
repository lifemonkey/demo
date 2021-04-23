package com.example.demo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "gpx")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Gps implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "creator")
    private String creator;

    @XmlElement(name = "metadata")
    @OneToOne(mappedBy = "gps", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Metadata metadata;

    @XmlElement(name = "wpt")
    @OneToMany(mappedBy = "gps", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Waypoint> waypoints;

    @XmlElementWrapper(name = "trk")
    @XmlElement(name = "trkseg")
    @OneToMany(mappedBy = "gps", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TrackSegment> trackSegments;

    public Gps() {
    }

    public Gps(String version, String creator, Metadata metadata, List<Waypoint> waypoints, List<TrackSegment> trackSegments) {
        this.version = version;
        this.creator = creator;
        this.metadata = metadata;
        this.waypoints = waypoints;
        this.trackSegments = trackSegments;
    }

    public void addWaypoint(Waypoint waypoint) {
        waypoints.add(waypoint);
        waypoint.setGps(this);
    }

    public void removeWaypoint(Waypoint waypoint) {
        waypoints.remove(waypoint);
        waypoint.setGps(null);
    }

    public void addTrackSegment(TrackSegment trackSegment) {
        trackSegments.add(trackSegment);
        trackSegment.setGps(this);
    }

    public void removeTrackSegment(TrackSegment trackSegment) {
        trackSegments.remove(trackSegment);
        trackSegment.setGps(null);
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

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        if (metadata == null) {
            if (this.metadata != null) {
                this.metadata.setGps(null);
            }
        } else {
            metadata.setGps(this);
        }

        this.metadata = metadata;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public List<TrackSegment> getTrackSegments() {
        return trackSegments;
    }

    public void setTrackSegments(List<TrackSegment> trackSegments) {
        this.trackSegments = trackSegments;
    }

    @Override
    public String toString() {
        return "Gps{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", creator='" + creator + '\'' +
                ", metadata=" + metadata +
                ", waypoints=" + waypoints +
                ", trackSegments=" + trackSegments +
                '}';
    }
}
