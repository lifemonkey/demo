package com.example.demo.controller;

import com.example.demo.DataBuilder;
import com.example.demo.domain.*;
import com.example.demo.dto.GPSDto;
import com.example.demo.dto.mapper.GPSMapper;
import com.example.demo.dto.mapper.GPSMapperImpl;
import com.example.demo.repository.*;
import com.example.demo.service.GPSTrackingService;
import com.example.demo.service.impl.GPSTrackingServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;


public class GPSTrackingServiceUT {

    final String filePath = System.getProperty("user.dir") + "/sample/sample.gpx";

    private GPSMapperImpl gpsMapper;

    private MockMultipartFile mockMultipartFile;

    private GPSRepository mockGpsRepository = Mockito.mock(GPSRepository.class);
    private MetadataRepository mockMetadataRepository = Mockito.mock(MetadataRepository.class);
    private LinkRepository mockLinkRepository = Mockito.mock(LinkRepository.class);
    private TrackpointRepository mockTrackpointRepository = Mockito.mock(TrackpointRepository.class);
    private TrackSegmentRepository mockTrackSegmentRepository = Mockito.mock(TrackSegmentRepository.class);
    private WaypointRepository mockWaypointRepository = Mockito.mock(WaypointRepository.class);
    private GPSMapper mockGpsMapper = Mockito.mock(GPSMapper.class);

    private GPSTrackingService gpsTrackingService = new GPSTrackingServiceImpl(
            mockGpsRepository,
            mockMetadataRepository,
            mockLinkRepository,
            mockTrackpointRepository,
            mockTrackSegmentRepository,
            mockWaypointRepository,
            mockGpsMapper);

    @BeforeEach
    void setup() throws IOException {
        File file = new File(filePath);
        gpsMapper = new GPSMapperImpl();

        mockMultipartFile = new MockMultipartFile(
                "test-gpx",
                file.getName(),
                MediaType.TEXT_PLAIN_VALUE,
                Files.readAllBytes(file.toPath()));
    }

    @AfterEach
    void teardown() {
        mockMultipartFile = null;
    }

    @Test
    void uploadFile_Test() {

        Gps gps = DataBuilder.buildGps();
        Mockito.when(mockGpsRepository.save((Gps) Mockito.any())).thenReturn(gps);
        Mockito.when(mockMetadataRepository.save((Metadata) Mockito.any())).thenReturn(gps.getMetadata());
        Mockito.when(mockLinkRepository.save((Link) Mockito.any())).thenReturn(gps.getMetadata().getLink());
        Mockito.when(mockGpsMapper.toDto(Mockito.any())).thenReturn(gpsMapper.toDto(gps));

        GPSDto gpsDto = gpsTrackingService.uploadFile(mockMultipartFile);
        Assertions.assertNotNull(gpsDto);
        Assertions.assertTrue(gpsDto.getId() == 1);
        Assertions.assertEquals("Test version", gpsDto.getVersion());
        Assertions.assertEquals("Test creator", gpsDto.getCreator());
    }

    @Test
    void latestTracks_Test() {
        List<Gps> gpsList = Arrays.asList(DataBuilder.buildGps());
        Mockito.when(mockGpsRepository.findAll()).thenReturn(gpsList);
        Mockito.when(mockGpsMapper.toDtos(Mockito.any())).thenReturn(gpsMapper.toDtos(gpsList));

        List<GPSDto> results = gpsTrackingService.latestTracks();
        Assertions.assertTrue(results.size() == 1);
    }

    @Test
    void trackDetails_Test() {
        Gps gps = DataBuilder.buildGps();
        Mockito.when(mockGpsRepository.findOne((Long) Mockito.any())).thenReturn(gps);
        Mockito.when(mockGpsMapper.toDto(Mockito.any())).thenReturn(gpsMapper.toDto(gps));

        GPSDto result = gpsTrackingService.trackDetails((long) 1);
        Assertions.assertNotNull(result);
    }
}
