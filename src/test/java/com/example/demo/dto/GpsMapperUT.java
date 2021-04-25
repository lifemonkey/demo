package com.example.demo.dto;

import com.example.demo.DataBuilder;
import com.example.demo.domain.*;
import com.example.demo.dto.mapper.GPSMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class GpsMapperUT {

    private GPSMapperImpl gpsMapper;

    private Gps gps;

    @BeforeEach
    void setup() {
        gpsMapper = new GPSMapperImpl();

        // init gps
        gps = DataBuilder.buildGps();
    }

    @AfterEach
    void teardown() {
        gps = null;
    }

    @Test
    public void toEntity_Test() {
        GPSDto gpsDto = gpsMapper.toDto(gps);

        Assert.assertNotNull(gpsDto);
        Assert.assertEquals("Test creator", gpsDto.getCreator());
        Assert.assertEquals("Test version", gpsDto.getVersion());
        // verify metadata mapper
        Assert.assertEquals("Metadata name", gpsDto.getMetadata().getName());
        Assert.assertEquals("Test Href", gpsDto.getMetadata().getLink());
        Assert.assertEquals("Test text", gpsDto.getMetadata().getLinkTxt());
        Assert.assertEquals("2021-04-25T22:22:22.222", gpsDto.getMetadata().getTime());
        // verify trackpoints
        Assert.assertEquals(2, gpsDto.getTrackpoints().size());
        Assert.assertTrue(gpsDto.getTrackpoints().get(0).getLongitude().doubleValue() == 11.11);
        Assert.assertTrue(gpsDto.getTrackpoints().get(0).getLatitude().doubleValue() == 22.22);
        // verify waypoints
        Assert.assertEquals(2, gpsDto.getWaypoints().size());
        Assert.assertEquals("Waypoint name 1", gpsDto.getWaypoints().get(0).getName());
        Assert.assertTrue(gpsDto.getWaypoints().get(0).getLongitude().doubleValue() == 11.11);
        Assert.assertTrue(gpsDto.getWaypoints().get(0).getLatitude().doubleValue() == 22.22);
        Assert.assertEquals("Waypoint name 2", gpsDto.getWaypoints().get(1).getName());
        Assert.assertTrue(gpsDto.getWaypoints().get(1).getLongitude().doubleValue() == 11.11);
        Assert.assertTrue(gpsDto.getWaypoints().get(1).getLatitude().doubleValue() == 22.22);
    }
}
