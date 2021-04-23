package com.example.demo.dto.mapper;

import com.example.demo.domain.*;
import com.example.demo.dto.GPSDto;
import com.example.demo.dto.MetadataDto;
import com.example.demo.dto.TrackpointDto;
import com.example.demo.dto.WaypointDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GPSMapperImpl implements GPSMapper {

    @Override
    public GPSDto toDto(Gps gps) {
        if (gps == null) {
            return null;
        }

        GPSDto gpsDto = new GPSDto();
        gpsDto.setId(gps.getId());
        gpsDto.setCreator(gps.getCreator());
        gpsDto.setMetadata(buildMetadata(gps.getMetadata()));
        gpsDto.setWaypoints(buildWaypoints(gps.getWaypoints()));
        gpsDto.setTrackpoints(buildTrackpoints(gps.getTrackSegments()));
        return gpsDto;
    }

    private MetadataDto buildMetadata(Metadata metadata) {
        if (metadata == null) {
            return null;
        }

        MetadataDto metadataDto = new MetadataDto();
        metadataDto.setId(metadata.getId());
        metadataDto.setName(metadata.getName());
        metadataDto.setDesc(metadata.getDesc());
        metadataDto.setAuthor(metadata.getAuthor());
        if (metadata.getLink() != null) {
            metadataDto.setLink(metadata.getLink().getHref());
            metadataDto.setLinkTxt(metadata.getLink().getText());
        }
        metadataDto.setTime(metadata.getTime());

        return metadataDto;
    }

    private List<WaypointDto> buildWaypoints(List<Waypoint> waypoints) {
        return waypoints.stream().map(wp -> buildWaypoint(wp)).collect(Collectors.toList());
    }

    private WaypointDto buildWaypoint(Waypoint waypoint) {
        if (waypoint == null) {
            return null;
        }

        WaypointDto waypointDto = new WaypointDto();
        waypointDto.setId(waypoint.getId());
        waypointDto.setLatitude(waypoint.getLatitude());
        waypointDto.setLongitude(waypoint.getLongitude());
        waypointDto.setName(waypoint.getName());
        waypointDto.setSymbol(waypoint.getSymbol());

        return waypointDto;
    }

    private List<TrackpointDto> buildTrackpoints(List<TrackSegment> trackSegments) {
        List<Trackpoint> trackpoints = new ArrayList<>();
        trackSegments.forEach(ts -> trackpoints.addAll(ts.getTrackpoints()));

        return trackpoints.stream().map(tp -> buildTrackpoint(tp)).collect(Collectors.toList());
    }

    private TrackpointDto buildTrackpoint(Trackpoint trackpoint) {
        TrackpointDto trackpointDto = new TrackpointDto();
        trackpointDto.setId(trackpoint.getId());
        trackpointDto.setElement(trackpoint.getElement());
        trackpointDto.setLatitude(trackpoint.getLatitude());
        trackpointDto.setLongitude(trackpoint.getLongitude());
        trackpointDto.setTime(trackpoint.getTime());

        return trackpointDto;
    }

    @Override
    public List<GPSDto> toDtos(List<Gps> gpsList) {
        if (gpsList == null) {
            return new ArrayList<>();
        }

        return gpsList.stream().map(gps -> toDto(gps)).collect(Collectors.toList());
    }
}
