package mipt.baranov.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class ExcelTemplate {
    private ExcelTemplate() {}

    public static void write(Path path, ExcelPresenter presenter) throws FileNotFoundException, IOException {
        try(FileOutputStream out = new FileOutputStream(path.toFile());
            Workbook workbook = new HSSFWorkbook()) {
            presenter.present(workbook);
            workbook.write(out);
        }
    }
}
