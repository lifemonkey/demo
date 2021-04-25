package com.example.demo.dto.mapper;

import com.example.demo.domain.*;
import com.example.demo.dto.GPSDto;
import com.example.demo.dto.MetadataDto;
import com.example.demo.dto.TrackpointDto;
import com.example.demo.dto.WaypointDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GPSMapperImpl implements GPSMapper {

    @Override
    public GPSDto toDto(Gps gps) {
        if (gps == null) {
            return null;
        }

        return new GPSDto()
                .withId(gps.getId())
                .withCreator(gps.getCreator())
                .withVersion(gps.getVersion())
                .withMetadata(buildMetadata(gps.getMetadata()))
                .withWaypoints(buildWaypoints(gps.getWaypoints()))
                .withTrackpoints(buildTrackpoints(gps.getTrackSegments()));
    }

    private MetadataDto buildMetadata(Metadata metadata) {
        if (metadata == null) {
            return null;
        }

        return new MetadataDto()
                .withId(metadata.getId())
                .withName(metadata.getName())
                .withDesc(metadata.getDesc())
                .withAuthor(metadata.getAuthor())
                .withLink(metadata.getLink() != null ? metadata.getLink().getHref() : null)
                .withLinkTxt(metadata.getLink() != null ? metadata.getLink().getText() : null)
                .withTime(metadata.getTime());
    }

    private List<WaypointDto> buildWaypoints(List<Waypoint> waypoints) {
        return waypoints.stream().map(wp -> buildWaypoint(wp)).collect(Collectors.toList());
    }

    private WaypointDto buildWaypoint(Waypoint waypoint) {
        if (waypoint == null) {
            return null;
        }

        return new WaypointDto()
                .withId(waypoint.getId())
                .withLatitude(waypoint.getLatitude())
                .withLongitude(waypoint.getLongitude())
                .withName(waypoint.getName())
                .withSymbol(waypoint.getSymbol());
    }

    private List<TrackpointDto> buildTrackpoints(List<TrackSegment> trackSegments) {
        List<Trackpoint> trackpoints = new ArrayList<>();
        trackSegments.forEach(ts -> trackpoints.addAll(ts.getTrackpoints()));

        return trackpoints.stream().map(tp -> buildTrackpoint(tp)).collect(Collectors.toList());
    }

    private TrackpointDto buildTrackpoint(Trackpoint trackpoint) {
        return new TrackpointDto()
                .withId(trackpoint.getId())
                .withElement(trackpoint.getElement())
                .withLatitude(trackpoint.getLatitude())
                .withLongitude(trackpoint.getLongitude())
                .withTime(trackpoint.getTime());
    }

    @Override
    public List<GPSDto> toDtos(List<Gps> gpsList) {
        if (gpsList == null) {
            return new ArrayList<>();
        }

        return gpsList.stream().map(gps -> toDto(gps)).collect(Collectors.toList());
    }
}
