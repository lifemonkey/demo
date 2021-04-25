package com.example.demo;

import com.example.demo.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBuilder {

    public static Gps buildGps() {
        LocalDate localDate = LocalDate.now();

        // init link
        Link link = new Link().withHref("Test Href").withText("Test text");

        // init metadata
        Metadata metadata = new Metadata()
                .withId((long) 1)
                .withAuthor("Test author")
                .withName("Metadata name")
                .withDesc("Test description")
                .withLink(link)
                .withTime(localDate);

        // init trackpoints
        List<Trackpoint> trackpoints = new ArrayList<>();
        trackpoints.add(new Trackpoint()
                .withId((long) 1)
                .withElement("Test element 1")
                .withLongitude(11.11)
                .withLatitude(22.22)
                .withTime(localDate));
        trackpoints.add(new Trackpoint()
                .withId((long) 2)
                .withElement("Test element 2")
                .withLongitude(11.11)
                .withLatitude(22.22)
                .withTime(localDate));

        // init tracksegments
        List<TrackSegment> trackSegments = new ArrayList<>();
        trackSegments.add(new TrackSegment().withTrackpoints(trackpoints));

        // init waypoints
        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(new Waypoint()
                .withId((long) 1)
                .withSymbol("Test symbol 1")
                .withName("Waypoint name 1")
                .withLongitude(11.11)
                .withLatitude(22.22));
        waypoints.add(new Waypoint()
                .withId((long) 2)
                .withSymbol("Test symbol 2")
                .withName("Waypoint name 2")
                .withLongitude(11.11)
                .withLatitude(22.22));

        // init gps
        return new Gps()
                .withId((long) 1)
                .withCreator("Test creator")
                .withVersion("Test version")
                .withMetadata(metadata)
                .withTrackSegments(trackSegments)
                .withWaypoints(waypoints);
    }
}
