package com.example.demo.service;

import com.example.demo.dto.GPSDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GPSTrackingService {

    GPSDto uploadFile(MultipartFile file);

    List<GPSDto> latestTracks();

    GPSDto trackDetails(long id);
}
