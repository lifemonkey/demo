package com.example.demo.service.impl;

import com.example.demo.domain.*;
import com.example.demo.dto.GPSDto;
import com.example.demo.dto.mapper.GPSMapper;
import com.example.demo.helpers.Utils;
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

    public GPSTrackingServiceImpl(GPSRepository gpsRepository, MetadataRepository metadataRepository, LinkRepository linkRepository, TrackpointRepository trackpointRepository, TrackSegmentRepository trackSegmentRepository, WaypointRepository waypointRepository, GPSMapper gpsMapper) {
        this.gpsRepository = gpsRepository;
        this.metadataRepository = metadataRepository;
        this.linkRepository = linkRepository;
        this.trackpointRepository = trackpointRepository;
        this.trackSegmentRepository = trackSegmentRepository;
        this.waypointRepository = waypointRepository;
        this.gpsMapper = gpsMapper;
    }

    public GPSDto uploadFile(MultipartFile file) {

        if (file == null || !Utils.getFileExtension(file.getOriginalFilename()).equals("gpx")) {
            return null;
        }

        try {
            // read xml file as gpx format
            Gps gps = XMLReader.parseXML(file.getInputStream());

            if (gps != null) {
                Gps gpsInDb = gpsRepository.save(gps);
                // update parentId to children record
                updateParentId(gpsInDb);
                // return inserted data
                return gpsMapper.toDto(gpsInDb);
            }
        } catch (IOException e) {
            log.error("Could not upload file. " + e.getMessage());
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
        log.debug("Get " + gpsList.size() + " tracks");

        return gpsMapper.toDtos(gpsList);
    }

    public GPSDto trackDetails(long id) {
        GPSDto gpsDto = new GPSDto();
        log.debug("Get track details by ID " + id);

        if (id > 0) {
            Gps gps = gpsRepository.findOne(id);
            if (gps != null) {
                gpsDto = gpsMapper.toDto(gps);
                log.debug("Track details: \n" + gpsDto.toString());
            }
        }

        return gpsDto;
    }
}
