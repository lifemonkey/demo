package com.example.demo.domain;

import com.example.demo.helpers.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "trkpt")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Trackpoint {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @XmlAttribute(name = "lat")
    private Double latitude;
    @XmlAttribute(name = "lon")
    private Double longitude;
    @XmlElement(name = "ele")
    private String element;
    @XmlElement(name = "time")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_segment_id")
    TrackSegment trackSegment;

    public Trackpoint() {
    }

    public Trackpoint(Double latitude, Double longitude, String element, LocalDateTime time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.element = element;
        this.time = time;
    }

    public Trackpoint withId(Long id) {
        this.id = id;
        return this;
    }

    public Trackpoint withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Trackpoint withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Trackpoint withElement(String element) {
        this.element = element;
        return this;
    }

    public Trackpoint withTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public TrackSegment getTrackSegment() {
        return trackSegment;
    }

    public void setTrackSegment(TrackSegment trackSegment) {
        this.trackSegment = trackSegment;
    }

    @Override
    public String toString() {
        return "Trackpoint{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", element='" + element + '\'' +
                ", time=" + time +
                ", trackSegment=" + trackSegment +
                '}';
    }
}
