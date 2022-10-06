package mipt.baranov.excel;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.nio.file.Path;

@FunctionalInterface
public interface ExcelPresenter {
    void present(Workbook workbook) throws IOException;
}
