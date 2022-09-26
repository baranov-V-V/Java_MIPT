package mipt.baranov.dao.H2;

import lombok.AllArgsConstructor;
import mipt.baranov.JDBS.JdbcTemplate;
import mipt.baranov.dao.Dao;
import mipt.baranov.entities.Flight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
                statement.setString(7, entity.getStatus());
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
            ResultSet resultSet = statement.executeQuery("select * from airports");
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
                set.getString(7),
                set.getString(8),
                Objects.nonNull(set.getString(9)) ? ZonedDateTime.parse(set.getString(9)) : null,
                Objects.nonNull(set.getString(10)) ? ZonedDateTime.parse(set.getString(10)) : null
        );
    }
}
