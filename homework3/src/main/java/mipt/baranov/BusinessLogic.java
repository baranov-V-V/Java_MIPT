package mipt.baranov;

import mipt.baranov.database.Database;
import mipt.baranov.database.dao.H2.AirportDao;
import mipt.baranov.database.dao.H2.FlightDao;
import mipt.baranov.database.dao.H2.TicketDao;
import mipt.baranov.database.dto.*;
import mipt.baranov.excel.ExcelPresenters;
import mipt.baranov.graphics.ChartPresenters;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

public class BusinessLogic {
    private final Database database;

    public BusinessLogic(Database database) {
        this.database = database;
    }

    public void doTask1() throws SQLException, IOException {
        AirportDao portDao = new AirportDao(database.getConnection());

        List<MultiAirportCity> cities = portDao.getCitiesWithManyAirports();
        ExcelPresenters.presentCitiesWithManyAirports(cities, Paths.get("cities_with_many_airports.xls"));
    }

    public void doTask2() throws SQLException, IOException {
        FlightDao flightDao = new FlightDao(database.getConnection());

        List<CancelledFlightCities> cities = flightDao.getMostCancelledCities();
        ExcelPresenters.presentMostCancelledFilghtsCities(cities, Paths.get("most_cancelled_flights_cities.xls"));
    }

    public void doTask3() throws SQLException, IOException {
        FlightDao flightDao = new FlightDao(database.getConnection());

        List<SmallestFligthByCity> flights = flightDao.getSmallestFlights();
        ExcelPresenters.presentSmallestFlightsByCity(flights, Paths.get("shortest_flights_in_cities.xls"));
    }

    public void doTask4() throws SQLException, IOException {
        FlightDao flightDao = new FlightDao(database.getConnection());

        List<CancelledCountByMonth> cancelled = flightDao.getCancelledByMonth();
        ExcelPresenters.presentCancelledFilghtsByMonth(cancelled, Paths.get("cancelled_by_month.xls"));
        ChartPresenters.chartCancelledFlightsByMonth(cancelled);
    }

    public void doTask5() throws SQLException, IOException {
        FlightDao flightDao = new FlightDao(database.getConnection());

        List<FlightsNumByWeekday> flights = flightDao.getFlightsNumInCityByWeekday("Moscow");
        ExcelPresenters.presentFlightsNumByCity(flights, "Москва", Paths.get("flights_in_moscow.xls"));
        ChartPresenters.chartFlightsNumByWeekday(flights);
    }

    public void doTask6(String model) throws SQLException {
        FlightDao flightDao = new FlightDao(database.getConnection());
        flightDao.cancelFlightsByAirplaneModel(model);
    }

    public void doTask7(LocalDate begin, LocalDate end) throws SQLException, IOException {
        FlightDao flightDao = new FlightDao(database.getConnection());

        List<LossesByWeekday> losses = flightDao.cancelFlightsByTimePeriod(LocalDateTime.of(begin, LocalTime.MIN), LocalDateTime.of(end, LocalTime.MAX), "Moscow");
        ExcelPresenters.presentLossesByWeekday(losses, "Москва", Paths.get("losses_of_moscow_flights.xls"));
        ChartPresenters.chartLossesByWeekday(losses);
    }

    public void doTask8(String model) throws SQLException {
        TicketDao ticketDao = new TicketDao(database.getConnection());
        ticketDao.addTicket();
    }
}
