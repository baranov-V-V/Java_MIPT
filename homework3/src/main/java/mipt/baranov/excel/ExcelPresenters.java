package mipt.baranov.excel;

import mipt.baranov.database.dto.*;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class ExcelPresenters {
    static final float MAX_FONT_SIZE = 1.45f;
    static final int HEADER_FONT_HEIGHT = 20;
    static final int CELLS_FONT_HEIGHT = 15;

    private ExcelPresenters() {}

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
                dataCell.setCellValue(String.join(", ", airports));
                counter.recalc(1, dataCell);
            });

            sheet.setColumnWidth(0, (int) (MAX_FONT_SIZE * (float) counter.get(0) * 256));
            sheet.setColumnWidth(1, (int) (MAX_FONT_SIZE * (float) counter.get(1) * 256));
        });
    }

    public static void presentCitiesWithManyAirports(List<MultiAirportCity> cities, Path path) throws FileNotFoundException, IOException {
        ExcelTemplate.write(path, workbook -> {
            final int columnNum = 2;
            ColumnsSizeCounter counter = new ColumnsSizeCounter(columnNum);

            CellStyle cellStyle = prepareWorkbook(workbook, "Города с множеством аэропортов", List.of("Город", "Список аэропортов"), counter);

            Sheet sheet = workbook.getSheetAt(0);

            cities.forEach(city -> {
                Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);

                Cell dataCell = dataRow.createCell(0);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(city.getCityName());
                counter.recalc(0, dataCell);

                dataCell = dataRow.createCell(1);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(String.join(", ", city.getAirportCodes()));
                counter.recalc(1, dataCell);
            });

            sheet.setColumnWidth(0, (int) (MAX_FONT_SIZE * (float) counter.get(0) * 256));
            sheet.setColumnWidth(1, (int) (MAX_FONT_SIZE * (float) counter.get(1) * 256));
        });
    }

    public static void presentMostCancelledFilghtsCities(List<CancelledFlightCities> cities, Path path) throws FileNotFoundException, IOException {
        ExcelTemplate.write(path, workbook -> {
            final int columnNum = 2;
            ColumnsSizeCounter counter = new ColumnsSizeCounter(columnNum);

            CellStyle cellStyle = prepareWorkbook(workbook, "Отмененные рейсы", List.of("Город", "Количество рейсов"), counter);

            Sheet sheet = workbook.getSheetAt(0);

            cities.forEach(city -> {
                Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);

                Cell dataCell = dataRow.createCell(0);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(city.getCityName());
                counter.recalc(0, dataCell);

                dataCell = dataRow.createCell(1);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(city.getCancelledNum().toString());
                counter.recalc(1, dataCell);
            });

            sheet.setColumnWidth(0, (int) (MAX_FONT_SIZE * (float) counter.get(0) * 256));
            sheet.setColumnWidth(1, (int) (MAX_FONT_SIZE * (float) counter.get(1) * 256));
        });
    }

    public static void presentCancelledFilghtsByMonth(List<CancelledCountByMonth> months, Path path) throws FileNotFoundException, IOException {
        ExcelTemplate.write(path, workbook -> {
            final int columnNum = 2;
            ColumnsSizeCounter counter = new ColumnsSizeCounter(columnNum);

            CellStyle cellStyle = prepareWorkbook(workbook, "Отмененные рейсы", List.of("Месяц", "Количество рейсов"), counter);

            Sheet sheet = workbook.getSheetAt(0);

            months.forEach(city -> {
                Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);

                Cell dataCell = dataRow.createCell(0);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(city.getMonth().toString());
                counter.recalc(0, dataCell);

                dataCell = dataRow.createCell(1);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(city.getCancelledNum().toString());
                counter.recalc(1, dataCell);
            });

            sheet.setColumnWidth(0, (int) (MAX_FONT_SIZE * (float) counter.get(0) * 256));
            sheet.setColumnWidth(1, (int) (MAX_FONT_SIZE * (float) counter.get(1) * 256));
        });
    }

    public static void presentLossesByWeekday(List<LossesByWeekday> days, String cityName, Path path) throws FileNotFoundException, IOException {
        ExcelTemplate.write(path, workbook -> {
            final int columnNum = 2;
            ColumnsSizeCounter counter = new ColumnsSizeCounter(columnNum);

            CellStyle cellStyle = prepareWorkbook(workbook, "Убытки в городе " + cityName, List.of("День", "Убытки"), counter);

            Sheet sheet = workbook.getSheetAt(0);

            days.forEach(day -> {
                Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);

                Cell dataCell = dataRow.createCell(0);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(day.getDay().toString());
                counter.recalc(0, dataCell);

                dataCell = dataRow.createCell(1);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(day.getLossAmount().toString());
                counter.recalc(1, dataCell);
            });

            sheet.setColumnWidth(0, (int) (MAX_FONT_SIZE * (float) counter.get(0) * 256));
            sheet.setColumnWidth(1, (int) (MAX_FONT_SIZE * (float) counter.get(1) * 256));
        });
    }

    public static void presentFlightsNumByCity(List<FlightsNumByWeekday> flights, String cityName, Path path) throws FileNotFoundException, IOException {
        ExcelTemplate.write(path, workbook -> {
            final int columnNum = 3;
            ColumnsSizeCounter counter = new ColumnsSizeCounter(columnNum);

            CellStyle cellStyle = prepareWorkbook(workbook, "Рейсы в городе " + cityName, List.of("День недели", "Количество вылетающих", "Количество прилетающих"), counter);

            Sheet sheet = workbook.getSheetAt(0);

            flights.forEach(flightDay -> {
                Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);

                Cell dataCell = dataRow.createCell(0);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(flightDay.getDay().toString());
                counter.recalc(0, dataCell);

                dataCell = dataRow.createCell(1);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(flightDay.getDepartureNum().toString());
                counter.recalc(1, dataCell);

                dataCell = dataRow.createCell(3);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(flightDay.getArrivalNum().toString());
                counter.recalc(2, dataCell);
            });

            sheet.setColumnWidth(0, (int) (MAX_FONT_SIZE * (float) counter.get(0) * 256));
            sheet.setColumnWidth(1, (int) (MAX_FONT_SIZE * (float) counter.get(1) * 256));
            sheet.setColumnWidth(2, (int) (MAX_FONT_SIZE * (float) counter.get(2) * 256));
        });
    }

    public static void presentSmallestFlightsByCity(List<SmallestFligthByCity> flights, Path path) throws FileNotFoundException, IOException {
        ExcelTemplate.write(path, workbook -> {
            final int columnNum = 3;
            ColumnsSizeCounter counter = new ColumnsSizeCounter(columnNum);

            CellStyle cellStyle = prepareWorkbook(workbook, "Самые короткие рейсы", List.of("Город", "Пунтк прибытия", "Длительность"), counter);

            Sheet sheet = workbook.getSheetAt(0);

            flights.forEach(flightDay -> {
                Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);

                Cell dataCell = dataRow.createCell(0);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(flightDay.getCityName());
                counter.recalc(0, dataCell);

                dataCell = dataRow.createCell(1);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(flightDay.getDestName());
                counter.recalc(1, dataCell);

                dataCell = dataRow.createCell(3);
                dataCell.setCellStyle(cellStyle);
                dataCell.setCellValue(flightDay.getFlightDuration().toString());
                counter.recalc(2, dataCell);
            });

            sheet.setColumnWidth(0, (int) (MAX_FONT_SIZE * (float) counter.get(0) * 256));
            sheet.setColumnWidth(1, (int) (MAX_FONT_SIZE * (float) counter.get(1) * 256));
            sheet.setColumnWidth(2, (int) (MAX_FONT_SIZE * (float) counter.get(2) * 256));
        });
    }

    private static CellStyle prepareWorkbook(Workbook workbook, String sheetName, List<String> columnNames, ColumnsSizeCounter counter) {
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

        return cellStyle;
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

