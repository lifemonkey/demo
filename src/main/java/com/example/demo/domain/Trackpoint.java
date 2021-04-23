package com.example.demo.domain;

import com.example.demo.helpers.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "trkpt")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Trackpoint {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @XmlAttribute(name = "lat")
    private Double latitude;
    @XmlAttribute(name = "lon")
    private Double longitude;
    @XmlElement(name = "ele")
    private String element;
    @XmlElement(name = "time")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_segment_id")
    TrackSegment trackSegment;

    public Trackpoint() {
    }

    public Trackpoint(Double latitude, Double longitude, String element, LocalDate time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.element = element;
        this.time = time;
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

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
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
