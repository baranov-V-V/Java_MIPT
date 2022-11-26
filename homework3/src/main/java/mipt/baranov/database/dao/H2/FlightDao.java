package mipt.baranov.database.dao.H2;

import lombok.AllArgsConstructor;
import mipt.baranov.database.JDBS.JdbcTemplate;
import mipt.baranov.database.dao.Dao;
import mipt.baranov.database.dto.*;
import mipt.baranov.entities.Flight;
import mipt.baranov.util.sql.H2.Converters;
import org.json.JSONObject;
import org.json.JSONString;

import java.sql.*;
import java.time.*;
import java.util.*;

@AllArgsConstructor
public class FlightDao implements Dao<Flight> {
    private final JdbcTemplate jdbc;

    @Override
    public void saveAll(Collection<Flight> entities) throws SQLException {
        jdbc.executePreparedStatement("insert into flights(" +
                "flight_id, " +
                "flight_no, " +
                "scheduled_departure, " +
                "scheduled_arrival, " +
                "departure_airport, " +
                "arrival_airport, " +
                "status, " +
                "aircraft_code, " +
                "actual_departure, " +
                "actual_arrival) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", statement -> {
            for (Flight entity : entities) {
                statement.setInt(1, entity.getFlightId());
                statement.setString(2, entity.getFlightNo());
                statement.setString(3, entity.getScheduledDeparture().toString());
                statement.setString(4, entity.getScheduledArrival().toString());
                statement.setString(5, entity.getDepartureAirport());
                statement.setString(6, entity.getArrivalAirport());
                statement.setString(7, entity.getStatus().getStringStatus());
                statement.setString(8, entity.getAircraftCode());
                statement.setString(9, entity.getActualDeparture() != null ?
                        entity.getActualDeparture().toString() : null);
                statement.setString(9, entity.getActualArrival() != null ?
                        entity.getActualArrival().toString() : null);
                statement.addBatch();
            }
            statement.executeBatch();
        });
    }

    @Override
    public List<Flight> getAll() throws SQLException {
        List<Flight> entities = new ArrayList<>();
        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select * from flights");
            while (resultSet.next()) {
                entities.add(createEntity(resultSet));
            }
        });
        return entities;
    }

    private Flight createEntity(ResultSet set) throws SQLException {
        return new Flight(
                set.getInt(1),
                set.getString(2),
                ZonedDateTime.parse(set.getString(3)),
                ZonedDateTime.parse(set.getString(4)),
                set.getString(5),
                set.getString(6),
                Flight.FlightStatus.valueOf(set.getString(7)),
                set.getString(8),
                Objects.nonNull(set.getString(9)) ? ZonedDateTime.parse(set.getString(9)) : null,
                Objects.nonNull(set.getString(10)) ? ZonedDateTime.parse(set.getString(10)) : null
        );
    }

    public List<CancelledFlightCities> getMostCancelledCities() throws SQLException {
        List<CancelledFlightCities> cities = new ArrayList<>();

        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery(
                    "select airports.city, count(*) from flights\n" +
                            "join airports on airports.airport_code = flights.departure_airport\n" +
                            "where status = 'Cancelled'\n" +
                            "group by airports.city\n" +
                            "order by count(*) desc");

            while (resultSet.next()) {
                //JSONObject city = resultSet.getObject(1, JSONObject.class);
                //JSONObject city = new JSONObject(resultSet.getString(1));

                //System.out.printf("Json: %s\n", resultSet.getString(1));

                //System.out.println(Converters.getJsonString(resultSet.getString(1)));

                String city = new JSONObject(Converters.getJsonString(resultSet.getString(1))).getString("ru");
                Integer num = resultSet.getInt(2);
                //resultSet.get
                //String value = jsonObject.getFirst("ru").toString();
                //System.out.println(value);

                System.out.printf("got %s %d\n", city, num);

                cities.add(new CancelledFlightCities(city, num));
            }
        });

        return cities;
    }

    public List<SmallestFligthByCity> getSmallestFlights() throws SQLException {
        List<SmallestFligthByCity> flights = new ArrayList<>();

        //ВСЕ Переписать тут невернооо!!
        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery(
                    "select airports.city, count(*) from flights\n" +
                            "join airports on airports.airport_code = flights.departure_airport\n" +
                            "where status = 'Cancelled'\n" +
                            "group by airports.city\n" +
                            "order by count(*) desc");

            while (resultSet.next()) {
                //JSONObject city = resultSet.getObject(1, JSONObject.class);
                //JSONObject city = new JSONObject(resultSet.getString(1));

                //System.out.printf("Json: %s\n", resultSet.getString(1));

                //System.out.println(Converters.getJsonString(resultSet.getString(1)));

                String city = new JSONObject(Converters.getJsonString(resultSet.getString(1))).getString("ru");
                Integer num = resultSet.getInt(2);
                //resultSet.get
                //String value = jsonObject.getFirst("ru").toString();
                //System.out.println(value);

                System.out.printf("got %s %d\n", city, num);

                //flights.add(new CancelledFlightCities(city, num));
            }
        });

        return flights;
    }

    public List<CancelledCountByMonth> getCancelledByMonth() throws SQLException {
        List<CancelledCountByMonth> cancelledFligths = new ArrayList<>();

        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery(
                    "select extract(MONTH from scheduled_departure), count(*) from flights\n" +
                            "where status = 'Cancelled'\n" +
                            "group by extract(MONTH from scheduled_departure)\n" +
                            "order by extract(MONTH from scheduled_departure) asc");

            while (resultSet.next()) {
                //JSONObject city = resultSet.getObject(1, JSONObject.class);
                //JSONObject city = new JSONObject(resultSet.getString(1));

                //System.out.printf("Json: %s\n", resultSet.getString(1));

                //System.out.println(Converters.getJsonString(resultSet.getString(1)));

                int monthNo = resultSet.getInt(1);
                int cancelledNo = resultSet.getInt(2);
                //resultSet.get
                //String value = jsonObject.getFirst("ru").toString();
                //System.out.println(value);

                System.out.printf("got %d %d\n", monthNo, cancelledNo);

                cancelledFligths.add(new CancelledCountByMonth(Month.of(monthNo), cancelledNo));
            }
        });

        return cancelledFligths;
    }

    public List<FlightsNumByWeekday> getFlightsNumInCityByWeekday(String cityName) throws SQLException {
        List<FlightsNumByWeekday> weekdays = List.of(   new FlightsNumByWeekday(DayOfWeek.MONDAY),
                                                        new FlightsNumByWeekday(DayOfWeek.TUESDAY),
                                                        new FlightsNumByWeekday(DayOfWeek.WEDNESDAY),
                                                        new FlightsNumByWeekday(DayOfWeek.THURSDAY),
                                                        new FlightsNumByWeekday(DayOfWeek.FRIDAY),
                                                        new FlightsNumByWeekday(DayOfWeek.SATURDAY),
                                                        new FlightsNumByWeekday(DayOfWeek.SUNDAY));

        jdbc.workWithConnection(connection -> {
            connection.setAutoCommit(false);

            AirportDao airportDao = new AirportDao(jdbc);
            List<String> airportCodes = airportDao.getListOfAirportCodes(cityName);

            Array airportCodesArr = connection.createArrayOf("VARCHAR(3)", airportCodes.toArray());

            try(PreparedStatement statement = connection.prepareStatement(
                    "select count(*) from flights\n" +
                        "where arrival_airport = ANY(?) and extract(ISO_DAY_OF_WEEK from scheduled_arrival) = ?\n"
            )) {
                for (FlightsNumByWeekday weekday : weekdays) {
                    statement.setArray(1, airportCodesArr);
                    statement.setInt(2, weekday.getDay().getValue());
                    ResultSet resultSet = statement.executeQuery();
                    resultSet.next();
                    weekday.setArrivalNum(resultSet.getInt(1));
                }
            }

            try(PreparedStatement statement = connection.prepareStatement(
                    "select count(*) from flights\n" +
                            "where departure_airport = ANY(?) and extract(ISO_DAY_OF_WEEK from scheduled_departure) = ?\n"
            )) {
                for (FlightsNumByWeekday weekday : weekdays) {
                    statement.setArray(1, airportCodesArr);
                    statement.setInt(2, weekday.getDay().getValue());
                    ResultSet resultSet = statement.executeQuery();
                    resultSet.next();
                    weekday.setDepartureNum(resultSet.getInt(1));
                }
            }

            connection.commit();
        });

        return weekdays;
    }

    public void cancelFlightsByAirplaneModel(String model) throws SQLException {
        jdbc.workWithConnection(connection -> {
            connection.setAutoCommit(false);

            try(PreparedStatement statement = connection.prepareStatement(
                    "update flights\n" +
                    "set status = 'Cancelled'\n" +
                    "where aircraft_code = ?")) {
                statement.setString(1, model);
                statement.executeUpdate();
            }

            try(PreparedStatement statement = connection.prepareStatement(
                    "delete from bookings\n" +
                    "where booking.book_ref in\n" +
                        "select book_ref from tickets\n" +
                        "where tickets.ticket_no in (\n" +
                            "   select ticket_flights.ticket_no from ticket_flights\n" +
                            "   where ticket_flights.flight_id in (\n" +
                            "       select flights.flight_id from flights\n" +
                            "       where flights.aircraft_code = ?))")) {
                statement.setString(1, model);
                statement.executeUpdate();
            }

            try(PreparedStatement statement = connection.prepareStatement(
                    "delete from tickets\n" +
                    "where tickets.ticket_no in (\n" +
                    "   select ticket_flights.ticket_no from ticket_flights\n" +
                    "   where ticket_flights.flight_id in (\n" +
                    "       select flights.flight_id from flights\n" +
                    "       where flights.aircraft_code = ?))")) {
                statement.setString(1, model);
                statement.executeUpdate();
            }

            try(PreparedStatement statement = connection.prepareStatement(
                    "delete from ticket_flights\n" +
                    "where ticket_flights.flight_id in (\n" +
                    "   select flights.flight_id from flights\n" +
                    "   where flights.aircraft_code = ?)")) {
                statement.setString(1, model);
                statement.executeUpdate();
            }

            connection.commit();
        });
    }

    public List<LossesByWeekday> cancelFlightsByTimePeriod(LocalDateTime begin, LocalDateTime end, String cityName) throws SQLException {
        List<LossesByWeekday> weekdays = List.of(   new LossesByWeekday(DayOfWeek.MONDAY),
                                                    new LossesByWeekday(DayOfWeek.TUESDAY),
                                                    new LossesByWeekday(DayOfWeek.WEDNESDAY),
                                                    new LossesByWeekday(DayOfWeek.THURSDAY),
                                                    new LossesByWeekday(DayOfWeek.FRIDAY),
                                                    new LossesByWeekday(DayOfWeek.SATURDAY),
                                                    new LossesByWeekday(DayOfWeek.SUNDAY));
        //сделать 2 запроса: один на получение кодов аэропортов, другой чтобы уже попарсить

        //-----???? write prepared statement and andd how to check == with JSON = ANY(?), ? = setArray

        //получаем всех флайт id
        //идем в тикет_флайтс и считаем убытки по sum(amount)

        jdbc.workWithConnection(connection -> {
            //connection.createArrayOf() // делатем все тут.

            AirportDao airportDao = new AirportDao(jdbc);
            List<String> airportCodes = airportDao.getListOfAirportCodes(cityName);

            Array airportCodesArr = connection.createArrayOf("VARCHAR(3)", airportCodes.toArray());

            try(PreparedStatement statement = connection.prepareStatement(
                    "update flights\n" +
                            "set status = 'Cancelled'\n" +
                            "where (departure_airport = ANY(?) and scheduled_departure <= ? and scheduled_departure >= ?) or\n" +
                            "   (arrival_airport = ANY(?) and scheduled_arrival <= ? and scheduled_arrival >= ?)"
            )) {
                statement.setArray(1, airportCodesArr);
                statement.setTimestamp(2, Timestamp.valueOf(end));
                statement.setTimestamp(3, Timestamp.valueOf(begin));
                statement.setArray(4, airportCodesArr);
                statement.setTimestamp(5, Timestamp.valueOf(end));
                statement.setTimestamp(6, Timestamp.valueOf(begin));
                statement.executeUpdate();
            }

            try(PreparedStatement statement = connection.prepareStatement(
                    "select sum(amount) from ticket_flights\n" +
                        "where flight_id in (\n" +
                            "select flight_id from flights where\n" +
                                "(departure_airport = ANY(?) and scheduled_departure <= ? and scheduled_departure >= ?) or\n" +
                                "(arrival_airport = ANY(?) and scheduled_arrival <= ? and scheduled_arrival >= ?)\n" +
                            ")"
            )) {
                for (LossesByWeekday weekday : weekdays) {
                    statement.setArray(1, airportCodesArr);
                    statement.setTimestamp(2, Timestamp.valueOf(end));
                    statement.setTimestamp(3, Timestamp.valueOf(begin));
                    statement.setArray(4, airportCodesArr);
                    statement.setTimestamp(5, Timestamp.valueOf(end));
                    statement.setTimestamp(6, Timestamp.valueOf(begin));
                    ResultSet resultSet = statement.executeQuery();
                    resultSet.next();
                    weekday.setLossAmount(resultSet.getDouble(1));
                }
            }

        });

    return weekdays;
    }
}
