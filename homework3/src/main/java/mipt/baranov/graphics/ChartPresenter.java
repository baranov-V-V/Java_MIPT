package mipt.baranov.graphics;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

@FunctionalInterface
public interface ChartPresenter {
    JFreeChart present(DefaultCategoryDataset dataset);
}
