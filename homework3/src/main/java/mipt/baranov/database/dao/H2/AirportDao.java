package mipt.baranov.database.dao.H2;

import lombok.AllArgsConstructor;
import mipt.baranov.database.JDBS.JdbcTemplate;
import mipt.baranov.database.dao.Dao;
import mipt.baranov.entities.Airport;
import mipt.baranov.util.Point;
import mipt.baranov.util.sql.H2.Converters;
import org.h2.util.json.JSONValue;
import org.json.JSONObject;
import org.json.JSONString;

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

    //WORKING WRONTTTGGG
    private Airport createEntity(ResultSet set) throws SQLException {
        return new Airport(
                set.getString(1),
                new JSONObject(set.getString(2)),
                new JSONObject(set.getString(3)),
                new Point(0, 0),
                set.getString(5)
        );
    }

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
                //JSONObject city = resultSet.getObject(1, JSONObject.class);
                //JSONObject city = new JSONObject(resultSet.getString(1));

                //System.out.printf("Json: %s\n", resultSet.getString(1));

                //System.out.println(Converters.getJsonString(resultSet.getString(1)));

                String city = new JSONObject(Converters.getJsonString(resultSet.getString(1))).getString("ru");

                //resultSet.get
                //String value = jsonObject.getFirst("ru").toString();
                //System.out.println(value);

                System.out.printf("got %s %s\n", city, airportCode);


                if (!cities.containsKey(city)) {
                    cities.put(city, new ArrayList<>());
                }
                cities.get(city).add(airportCode);
            }
        });
        return cities;
    }
}
