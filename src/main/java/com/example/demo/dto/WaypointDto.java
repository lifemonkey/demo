package com.example.demo.dto;

import java.io.Serializable;

public class WaypointDto implements Serializable {

    static final long serialVersionUID = 1L;

    private Long id;
    private Double latitude;
    private Double longitude;
    private String name;
    private String symbol;

    public WaypointDto() {
    }

    public WaypointDto(Long id, Double latitude, Double longitude, String name, String symbol) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.symbol = symbol;
    }

    public WaypointDto withId(Long id) {
        this.id = id;
        return this;
    }

    public WaypointDto withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public WaypointDto withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public WaypointDto withName(String name) {
        this.name = name;
        return this;
    }

    public WaypointDto withSymbol(String symbol) {
        this.symbol = symbol;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "WaypointDto{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
