package mipt.baranov.graphics;

import mipt.baranov.database.dto.CancelledNumFlightsByMonth;
import mipt.baranov.database.dto.FlightsNumByWeekday;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Month;
import java.util.List;
import java.util.Map;

public class ChartPresenters {
    private ChartPresenters() {};

    public static void chartCancelledFlightsByMonth(List<CancelledNumFlightsByMonth> data) throws FileNotFoundException, IOException {
        ChartTemplate.write(dataset -> {
            data.forEach(monthIntegerEntry -> {
                dataset.setValue(monthIntegerEntry.getCancelledNum(), "Number of Cancelled", monthIntegerEntry.getMonth().toString());
            });
            return ChartFactory.createBarChart("Cancelled Flights", "Month", "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        });
    }

    public static void chartFlightsNumByWeekday(List<FlightsNumByWeekday> data) throws FileNotFoundException, IOException {
        ChartTemplate.write(dataset -> {
            data.forEach(monthIntegerEntry -> {
                dataset.setValue(monthIntegerEntry.getCancelledNum(), "Number of Cancelled", monthIntegerEntry.getMonth().toString());
            });
            return ChartFactory.createBarChart("Cancelled Flights", "Month", "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
            ChartFactory.createStackedBarChart()
        });
    }
}
