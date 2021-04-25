package com.example.demo.service;

import com.example.demo.dto.GPSDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GPSTrackingService {

    /**
     * Upload gpx file and store mandatory information: Metadata, Waypoint, Trackpoint
     *
     * @param file MultipartFile
     * @return uploaded data
     */
    GPSDto uploadFile(MultipartFile file);

    /**
     * Get all uploaded GPS information from DB
     *
     * @return List<GPSDto>
     */
    List<GPSDto> latestTracks();

    /**
     * Get GPS details by the given ID
     *
     * @param id GPS ID
     * @return GPSDto
     */
    GPSDto trackDetails(long id);
}
