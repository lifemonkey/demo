package com.example.demo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "trkseg")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class TrackSegment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @XmlElement(name = "trkpt")
    @OneToMany(mappedBy = "trackSegment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trackpoint> trackpoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gps_id")
    Gps gps;

    public TrackSegment() {
    }

    public TrackSegment(List<Trackpoint> trackpoints) {
        this.trackpoints = trackpoints;
    }

    public Long getId() {
        return id;
    }

    public void addTrackpoint(Trackpoint trackpoint) {
        trackpoints.add(trackpoint);
        trackpoint.setTrackSegment(this);
    }

    public void removeTrackpoint(Trackpoint trackpoint) {
        trackpoints.remove(trackpoint);
        trackpoint.setTrackSegment(null);
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

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "TrackSegment{" +
                "id=" + id +
                ", trackpoints=" + trackpoints +
                ", gps=" + gps +
                '}';
    }
}
