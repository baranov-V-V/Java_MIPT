package mipt.baranov.graphics;

import mipt.baranov.database.dto.CancelledCountByMonth;
import mipt.baranov.database.dto.FlightsNumByWeekday;
import mipt.baranov.database.dto.LossesByWeekday;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ChartPresenters {
    private ChartPresenters() {};

    public static void chartCancelledFlightsByMonth(List<CancelledCountByMonth> data) throws FileNotFoundException, IOException {
        ChartTemplate.write(dataset -> {
            data.forEach(monthIntegerEntry -> {
                dataset.setValue(monthIntegerEntry.getCancelledNum(), "Number of Cancelled", monthIntegerEntry.getMonth().toString());
            });
            return ChartFactory.createBarChart("Cancelled Flights", "Month", "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        });
    }

    public static void chartLossesByWeekday(List<LossesByWeekday> data) throws FileNotFoundException, IOException {
        ChartTemplate.write(dataset -> {
            data.forEach(monthIntegerEntry -> {
                dataset.setValue(monthIntegerEntry.getLossAmount(), "Number of Cancelled", monthIntegerEntry.getDay().toString());
            });
            return ChartFactory.createBarChart("Losses", "Day", "Amount", dataset, PlotOrientation.VERTICAL, true, true, false);
        });
    }

    public static void chartFlightsNumByWeekday(List<FlightsNumByWeekday> data) throws FileNotFoundException, IOException {
        ChartTemplate.write(dataset -> {
            data.forEach(flightsNumByWeekday -> {
                dataset.addValue(flightsNumByWeekday.getArrivalNum(), "Arrival", flightsNumByWeekday.getDay().toString());
                dataset.addValue(flightsNumByWeekday.getDepartureNum(), "Departure", flightsNumByWeekday.getDay().toString());
            });
            return ChartFactory.createBarChart("Flights", "Day", "Num", dataset, PlotOrientation.VERTICAL, true, true, false);
        });
    }
}
