package com.example.demo.service.impl;

import com.example.demo.domain.*;
import com.example.demo.dto.GPSDto;
import com.example.demo.dto.mapper.GPSMapper;
import com.example.demo.helpers.XMLReader;
import com.example.demo.repository.*;
import com.example.demo.service.GPSTrackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GPSTrackingServiceImpl implements GPSTrackingService {

    static final Logger log = LoggerFactory.getLogger(GPSTrackingService.class);

    @Autowired
    GPSRepository gpsRepository;

    @Autowired
    MetadataRepository metadataRepository;

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    TrackpointRepository trackpointRepository;

    @Autowired
    TrackSegmentRepository trackSegmentRepository;

    @Autowired
    WaypointRepository waypointRepository;

    @Autowired
    GPSMapper gpsMapper;


    public GPSDto uploadFile(MultipartFile file) {

        if (file == null) {
            return null;
        }

        try {
            Gps gps = XMLReader.parseXML(file.getInputStream());

            if (gps != null) {
                Gps gpsInDb = gpsRepository.save(gps);
                // update parentId to children record
                updateParentId(gpsInDb);
                // return inserted data
                return gpsMapper.toDto(gpsInDb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void updateParentId(Gps gpsInDb) {
        // update id for metadata
        Metadata metadata = gpsInDb.getMetadata();
        metadata.setGps(gpsInDb);
        Metadata metaInDb = metadataRepository.save(metadata);
        // update id for link
        Link link = metaInDb.getLink();
        link.setMetadata(metaInDb);
        linkRepository.save(link);
        // update id for track segment
        List<TrackSegment> trackSegments = gpsInDb.getTrackSegments();
        trackSegments.forEach(ts -> ts.setGps(gpsInDb));
        List<TrackSegment> tsInDb = trackSegmentRepository.save(trackSegments);
        // update id for track point
        List<Trackpoint> trackpoints = new ArrayList<>();
        tsInDb.forEach(ts -> {
            List<Trackpoint> tmp = ts.getTrackpoints();
            tmp.forEach(t -> t.setTrackSegment(ts));
            trackpoints.addAll(tmp);
        });
        trackpointRepository.save(trackpoints);
        // update id for waypoint list
        List<Waypoint> waypoints = gpsInDb.getWaypoints();
        waypoints.forEach(wp -> wp.setGps(gpsInDb));
        waypointRepository.save(waypoints);
    }

    public List<GPSDto> latestTracks() {
        List<Gps> gpsList = gpsRepository.findAll();

        return gpsMapper.toDtos(gpsList);
    }

    public GPSDto trackDetails(long id) {
        GPSDto gpsDto = new GPSDto();

        if (id > 0) {
            Gps gps = gpsRepository.findOne(id);
            if (gps != null) {
                gpsDto = gpsMapper.toDto(gps);
            }
        }

        return gpsDto;
    }
}
