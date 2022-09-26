package mipt.baranov.dao.H2;

import lombok.AllArgsConstructor;
import mipt.baranov.JDBS.JdbcTemplate;
import mipt.baranov.dao.Dao;
import mipt.baranov.entities.Ticket;
import mipt.baranov.entities.TicketFlight;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class TicketFlightDao implements Dao<TicketFlight> {
    private final JdbcTemplate jdbc;

    @Override
    public void saveAll(Collection<TicketFlight> entities) throws SQLException {
        jdbc.executePreparedStatement("insert into ticket_flights(ticket_no, " +
                "flight_id, " +
                "fare_conditions, " +
                "amount) values (?, ?, ?, ?)", statement -> {
            for (TicketFlight entity : entities) {
                statement.setString(1, entity.getTicketNo());
                statement.setInt(2, entity.getFlightId());
                statement.setString(3, entity.getFareConditions());
                statement.setDouble(4, entity.getAmount());
                statement.addBatch();
            }
            statement.executeBatch();
        });
    }

    @Override
    public List<TicketFlight> getAll() throws SQLException {
        List<TicketFlight> entities = new ArrayList<>();
        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select * from airports");
            while (resultSet.next()) {
                entities.add(createEntity(resultSet));
            }
        });
        return entities;
    }

    private TicketFlight createEntity(ResultSet set) throws SQLException {
        return new TicketFlight(
                set.getString(1),
                set.getInt(2),
                set.getString(3),
                set.getDouble(4)
        );
    }
}
