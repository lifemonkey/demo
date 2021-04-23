package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class TrackpointDto implements Serializable {

    static final long serialVersionUID = 1L;

    private Long id;
    private Double latitude;
    private Double longitude;
    private String element;
    private LocalDate time;

    public TrackpointDto() {}

    public TrackpointDto(Long id, Double latitude, Double longitude, String element, LocalDate time) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TrackpointDto{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", element='" + element + '\'' +
                ", time=" + time +
                '}';
    }
}
