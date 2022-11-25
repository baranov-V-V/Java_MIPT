package mipt.baranov.database.dao.H2;

import lombok.AllArgsConstructor;
import mipt.baranov.database.JDBS.JdbcTemplate;
import mipt.baranov.database.dao.Dao;
import mipt.baranov.entities.Ticket;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class TicketDao implements Dao<Ticket> {
    private final JdbcTemplate jdbc;

    @Override
    public void saveAll(Collection<Ticket> entities) throws SQLException {
        jdbc.executePreparedStatement("insert into tickets(ticket_no, " +
                "book_ref, " +
                "passenger_id, " +
                "passenger_name, " +
                "contact_data) values (?, ?, ?, ?, ?)", statement -> {
            for (Ticket entity : entities) {
                statement.setString(1, entity.getTicketNo());
                statement.setString(2, entity.getBookRef());
                statement.setString(3, entity.getPassengerId());
                statement.setString(4, entity.getPassengerName());
                statement.setString(5, Objects.nonNull(entity.getContactData()) ?
                        entity.getContactData().toString() : null);
                statement.addBatch();
            }
            statement.executeBatch();
        });
    }

    @Override
    public List<Ticket> getAll() throws SQLException {
        List<Ticket> entities = new ArrayList<>();
        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select * from tickets");
            while (resultSet.next()) {
                entities.add(createEntity(resultSet));
            }
        });
        return entities;
    }

    private Ticket createEntity(ResultSet set) throws SQLException {
        return new Ticket(
                set.getString(1),
                set.getString(2),
                set.getString(3),
                set.getString(4),
                Objects.nonNull(set.getString(5)) ? set.getString(5) : null
        );
    }

    public void addTicket() {
        //do something
    }
}
