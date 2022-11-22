package mipt.baranov.graphics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Month;
import java.util.List;
import java.util.Map;

public class ChartPresenters {
    private ChartPresenters() {};

    public static void chartCancelledFlightsByMonth(List<Map.Entry<Month, Integer>> data) throws FileNotFoundException, IOException {
        ChartTemplate.write(dataset -> {
            data.forEach(monthIntegerEntry -> {
                dataset.setValue(monthIntegerEntry.getValue().doubleValue(), "Number of Cancelled", monthIntegerEntry.getKey());
            });
            return ChartFactory.createBarChart("Cancelled Flights", "Month", "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        });
    }
}