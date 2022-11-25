package mipt.baranov.graphics;

import mipt.baranov.excel.ExcelPresenter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChartTemplate {
    private ChartTemplate() {}

    public static void write(ChartPresenter presenter) throws FileNotFoundException, IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        JFreeChart chart = presenter.present(dataset);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.black);

        ChartFrame chartFrame = new ChartFrame("Record", chart, true);
        chartFrame.setVisible(true);
        chartFrame.setSize(800, 600);

        //ChartUtilities.saveChartAsJPEG(Paths.get("cities.jpeg").toFile(), chart, chartFrame.getWidth(), chartFrame.getHeight());
    }
}
