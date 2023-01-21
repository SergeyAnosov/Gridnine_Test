package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------------------------------");
        System.out.println("весь список полётов");
        List<Flight> testFlights = FlightBuilder.createFlights();
        System.out.println("размер списка = " + testFlights.size() + " ед.");
        testFlights.forEach(System.out::println);
        System.out.println("--------------------------------");

        System.out.println("фильтр 1. вылет до текущего момента времени");
        List<Flight> filter1 = Filter.filterBeforeNow(testFlights);
        System.out.println("размер списка = " + filter1.size() + " ед.");
        filter1.forEach(System.out::println);
        System.out.println("--------------------------------");

        System.out.println("фильтр 2. имеются сегменты с датой прилёта раньше даты вылета");
        List<Flight> filter2 = Filter.checkDates(testFlights);
        System.out.println("размер списка = " + filter2.size() + " ед.");
        filter2.forEach(System.out::println);
        System.out.println("--------------------------------");

        System.out.println("фильтр 3. общее время, проведённое на земле превышает два часа");
        List<Flight> filter3 = Filter.checkTimeOnEarth(testFlights);
        System.out.println("размер списка = " + filter3.size() + " ед.");
        filter3.forEach(System.out::println);
        System.out.println("--------------------------------");
    }
}
