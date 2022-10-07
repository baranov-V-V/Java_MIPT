package mipt.baranov.excel;

import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Presenters {
    static final float MAX_FONT_SIZE = 1.45f;
    static final int HEADER_FONT_HEIGHT = 20;
    static final int CELLS_FONT_HEIGHT = 15;

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

            final ColumnsSizeCounter counter = new ColumnsSizeCounter(2);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Город");
            counter.recalc(0, cell);

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Список Аэропортов");
            counter.recalc(1, cell);

            data.forEach((cityName, airports) -> {
                Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);

                Cell dataCell = dataRow.createCell(0);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(cityName);
                counter.recalc(0, dataCell);

                dataCell = dataRow.createCell(1);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(String.join(" ", airports));
                counter.recalc(1, dataCell);
            });

            sheet.setColumnWidth(0, (int) (MAX_FONT_SIZE * (float) counter.get(0) * 256));
            sheet.setColumnWidth(1, (int) (MAX_FONT_SIZE * (float) counter.get(1) * 256));
        });
    }

    public static void presentMostCancelledFilghtsCities(List<Map.Entry<String, Integer>> cities, Path path) throws FileNotFoundException, IOException {
        ExcelTemplate.write(path, workbook -> {
            final int columnNum = 2;
            ColumnsSizeCounter counter = new ColumnsSizeCounter(columnNum);

            prepareWorkbook(workbook, "Отмененные рейсы", List.of("Город", "Количество рейсов"), counter);


        });
    }

    private static void prepareWorkbook(Workbook workbook, String sheetName, List<String> columnNames, ColumnsSizeCounter counter) {
        Sheet sheet = workbook.createSheet();
        workbook.setSheetName(0, sheetName);

        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();

        font.setFontHeightInPoints((short) HEADER_FONT_HEIGHT);
        font.setColor(Font.COLOR_NORMAL);
        font.setBold(true);
        cellStyle.setFont(font);

        Row row = sheet.createRow(0);

        Iterator<String> namesIterator = columnNames.iterator();
        int cellNo = 0;
        while (namesIterator.hasNext()) {
            String name = namesIterator.next();

            Cell cell = row.createCell(cellNo);

            cell.setCellStyle(cellStyle);
            cell.setCellValue(name);
            counter.recalc(cellNo, cell);
            ++cellNo;
        }
    }

    private static class ColumnsSizeCounter {
        private final ArrayList<Integer> columnsSizes;

        public ColumnsSizeCounter(int maxColumns) {
            ArrayList<Integer> list = new ArrayList<>();
            list.ensureCapacity(maxColumns);
            columnsSizes = list;
            System.out.printf("Capacity: %d", maxColumns);
            for (int i = 0; i < maxColumns; ++i) {
                columnsSizes.add(i, 0);
            }
        }

        int get(int columnNum) {
            return columnsSizes.get(columnNum);
        }

        void recalc(int columnNum, Cell cell) {
            if (columnsSizes.get(columnNum) < cell.getStringCellValue().length()) {
                columnsSizes.set(columnNum, cell.getStringCellValue().length());
            }
        }
    }
}

