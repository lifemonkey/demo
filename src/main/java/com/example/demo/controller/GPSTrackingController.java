package com.example.demo.controller;

import com.example.demo.dto.GPSDto;
import com.example.demo.service.GPSTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class GPSTrackingController {

    @Autowired
    private GPSTrackingService gpsTrackingService;

    @PostMapping("/upload")
    public ResponseEntity<GPSDto> uploadGPX(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(gpsTrackingService.uploadFile(file));
    }

    @GetMapping("/latest-track")
    public ResponseEntity<List<GPSDto>> latestTrack() {
        return ResponseEntity.ok(gpsTrackingService.latestTracks());
    }

    @GetMapping("/track/{id}")
    public ResponseEntity<GPSDto> trackById(@PathVariable long id) {
        return ResponseEntity.ok(gpsTrackingService.trackDetails(id));
    }
}
