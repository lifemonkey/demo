package com.example.demo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "wpt")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Waypoint implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @XmlAttribute(name = "lat")
    private Double latitude;
    @XmlAttribute(name = "lon")
    private Double longitude;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "sym")
    private String symbol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gps_id")
    Gps gps;

    public Waypoint() {
    }

    public Waypoint(Double latitude, Double longitude, String name, String symbol) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.symbol = symbol;
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

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", gps=" + gps +
                '}';
    }
}
