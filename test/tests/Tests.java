package tests;

import com.gridnine.testing.Filter;
import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

public class Tests {

    private final List<Flight> testFlights = FlightBuilder.createFlights();

    @Test
    @DisplayName("filterBeforeNow")
    public void filterBeforeNow() {
        List<Flight> filter1 = Filter.filterBeforeNow(testFlights);
        Assertions.assertNotEquals(testFlights, filter1);
        Assertions.assertEquals(filter1.size(), 5);
        Assertions.assertFalse(filter1.contains(testFlights.get(2)));
    }

    @Test
    @DisplayName("checkDates")
    public void checkDates() {
        List<Flight> filter2 = Filter.checkDates(testFlights);
        Assertions.assertNotEquals(testFlights, filter2);
        Assertions.assertEquals(filter2.size(), 5);
        Assertions.assertFalse(filter2.contains(testFlights.get(3)));
    }

    @Test
    @DisplayName("checkOnEarthTime")
    public void checkOnEarthTime() {
        List<Flight> filter3 = Filter.checkTimeOnEarth(testFlights);
        Assertions.assertNotEquals(testFlights, filter3);
        Assertions.assertEquals(filter3.size(), 4);
        Assertions.assertFalse(filter3.contains(testFlights.get(4)));
        Assertions.assertFalse(filter3.contains(testFlights.get(5)));
    }
}
