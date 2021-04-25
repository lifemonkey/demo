package com.example.demo.dto;

import java.io.Serializable;

public class TrackpointDto implements Serializable {

    static final long serialVersionUID = 1L;

    private Long id;
    private Double latitude;
    private Double longitude;
    private String element;
    private String time;

    public TrackpointDto() {
    }

    public TrackpointDto(Long id, Double latitude, Double longitude, String element, String time) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.element = element;
        this.time = time;
    }

    public TrackpointDto withId(Long id) {
        this.id = id;
        return this;
    }

    public TrackpointDto withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public TrackpointDto withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public TrackpointDto withElement(String element) {
        this.element = element;
        return this;
    }

    public TrackpointDto withTime(String time) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
