package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Filter {

    //вылет до текущего момента времени
    public static List<Flight> filterBeforeNow(List<Flight> testFlights) {
        List<Flight> filter = new ArrayList<>();
        for (Flight flight : testFlights) {
            if ((flight.getSegments().stream()
                    .filter(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))
                    .collect(Collectors.toList()).isEmpty())) {
                filter.add(flight);
            }
        }
        return filter;
    }

    //имеются сегменты с датой прилёта раньше даты вылета
    public static List<Flight> checkDates(List<Flight> testFlights) {
        List<Flight> filter = new ArrayList<>();
        for (Flight flight : testFlights) {
            if ((flight.getSegments().stream()
                    .filter(segment -> segment.getDepartureDate().isAfter(segment.getArrivalDate()))
                    .collect(Collectors.toList()).isEmpty())) {
                filter.add(flight);
            }
        }
        return filter;
    }

    //общее время, проведённое на земле превышает два часа (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)
    public static List<Flight> checkTimeOnEarth(List<Flight> testFlights) {
        int TWO_HOURS = 2 * 60 * 60;
        return testFlights.stream().filter(flight ->
                Filter.calculateTimeOnEarth(flight.getSegments()) < TWO_HOURS).collect(Collectors.toList());
    }

    private static int calculateTimeOnEarth(List<Segment> segments) {

        segments.sort(Comparator.comparing(Segment::getDepartureDate));
        int duration = 0;
        if (segments.size() > 1) {
            for (int i = 0; i < segments.size() - 1; i++) {
                duration += Duration.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate()).getSeconds();
            }
        }
        return duration;
    }
}
