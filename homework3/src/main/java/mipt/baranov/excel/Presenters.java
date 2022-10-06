package mipt.baranov.excel;

import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Presenters {
    private Presenters() {}

    public static void presentCitiesWithManyAirports(Map<String, List<String>> data, Path path) throws FileNotFoundException, IOException {
        ExcelTemplate.write(path, workbook -> {
            Sheet sheet = workbook.createSheet();
            workbook.setSheetName(0, "Города с множеством аэропортов");
            Row row = null;
            Cell cell = null;
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short) 10);
            font.setColor(Font.COLOR_NORMAL);
            cellStyle.setFont(font);

            row = sheet.createRow(0);

            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Город");

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Список Аэропортов");

            data.forEach((cityName, airports) -> {
                Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);

                Cell dataCell = dataRow.createCell(0);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(cityName);

                dataCell = dataRow.createCell(1);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(String.join(" ", airports));
            });
        });
    }
}
