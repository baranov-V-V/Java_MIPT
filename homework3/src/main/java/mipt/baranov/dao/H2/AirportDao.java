package mipt.baranov.dao.H2;

import lombok.AllArgsConstructor;
import mipt.baranov.JDBS.JdbcTemplate;
import mipt.baranov.dao.Dao;
import mipt.baranov.entities.Aircraft;
import mipt.baranov.entities.Airport;
import mipt.baranov.util.Point;
import mipt.baranov.util.sql.H2.Converters;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
}
