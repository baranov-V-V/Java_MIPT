package mipt.baranov.database.dao.H2;

import lombok.AllArgsConstructor;
import mipt.baranov.database.JDBS.JdbcTemplate;
import mipt.baranov.database.dao.Dao;
import mipt.baranov.database.dto.MultiAirportCity;
import mipt.baranov.entities.Airport;
import mipt.baranov.util.Point;
import mipt.baranov.util.sql.H2.Converters;
import org.h2.util.json.JSONValue;
import org.json.JSONObject;
import org.json.JSONString;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

@AllArgsConstructor
public class AirportDao implements Dao<Airport> {
    private final JdbcTemplate jdbc;

    @Override
    public void saveAll(Collection<Airport> entities) throws SQLException {
        jdbc.executePreparedStatement("insert into airports(airport_code, airport_name, city, coordinates, timezone) values (?, ?, ?, ?, ?)", statement -> {
            for (Airport entity : entities) {
                statement.setString(1, entity.getAirportCode());
                statement.setString(2, entity.getAirportName().toString());
                statement.setString(3, entity.getCity().toString());
                statement.setString(4, Converters.pointToString(entity.getCoordinates()));
                statement.setString(5, entity.getTimezone());
                statement.addBatch();
            }
            statement.executeBatch();
        });
    }

    @Override
    public List<Airport> getAll() throws SQLException {
        List<Airport> entities = new ArrayList<>();
        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select * from airports");
            while (resultSet.next()) {
                entities.add(createEntity(resultSet));
            }
        });
        return entities;
    }

    //WORKING WRONTTTGGG fix point
    private Airport createEntity(ResultSet set) throws SQLException {
        return new Airport(
                set.getString(1),
                set.getString(3),
                set.getString(2),
                new Point(0, 0),
                set.getString(5)
        );
    }

    public List<MultiAirportCity> getCitiesWithManyAirports() throws SQLException {
        List<MultiAirportCity> cities = new ArrayList<>();

        Map<String, MultiAirportCity> citiesMap = new HashMap<>();

        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery(
                    "select city, airport_code from airports where\n" +
                            "    city in (\n" +
                            "        select city from airports\n" +
                            "        group by city\n" +
                            "        having count(airport_code) > 1)");
            String currCity = null;
            while (resultSet.next()) {
                String airportCode = resultSet.getString(2);
                String city = new JSONObject(Converters.getJsonString(resultSet.getString(1))).getString("ru");

                System.out.printf("got %s %s\n", city, airportCode);

                if (!citiesMap.containsKey(city)) {
                    citiesMap.put(city, new MultiAirportCity(city, new ArrayList<>()));
                }
                citiesMap.get(city).getAirportCodes().add(airportCode);
            }
        });
        return cities;
    }

    public List<String> getListOfAirportCodes(String city) throws SQLException {
        List<String> airportCodes = new ArrayList<>();

        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select city, airport_code from airports;");
            while (resultSet.next()) {
                JSONObject cityJson = new JSONObject(Converters.getJsonString(resultSet.getString(1)));
                System.out.println(cityJson.toString());
                if (cityJson.get("en").equals(city)) {
                    airportCodes.add(resultSet.getString(2));
                }
            }
        });

        return airportCodes;
    }

     /*
    public Map<String, List<String>> getCitiesWithManyAirports() throws SQLException {
        Map<String, List<String>> cities = new HashMap<>();

        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery(
                    "select city, airport_code from airports where\n" +
                            "    city in (\n" +
                            "        select city from airports\n" +
                            "        group by city\n" +
                            "        having count(airport_code) > 1)");

            while (resultSet.next()) {
                String airportCode = resultSet.getString(2);
                String city = new JSONObject(Converters.getJsonString(resultSet.getString(1))).getString("ru");
                System.out.printf("got %s %s\n", city, airportCode);

                if (!cities.containsKey(city)) {
                    cities.put(city, new ArrayList<>());
                }
                cities.get(city).add(airportCode);
            }
        });
        return cities;
    }
    */
}
