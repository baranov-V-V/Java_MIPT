package mipt.baranov.database.dao.H2;

import lombok.AllArgsConstructor;
import mipt.baranov.database.JDBS.JdbcTemplate;
import mipt.baranov.database.dao.Dao;
import mipt.baranov.entities.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class BookingDao implements Dao<Booking> {
    private final JdbcTemplate jdbc;

    @Override
    public void saveAll(Collection<Booking> entities) throws SQLException {
        jdbc.executePreparedStatement("insert into bookings(book_ref, book_date, total_amount) values (?, ?, ? as DECIMAL(10, 2))", statement -> {
            for (Booking entity : entities) {
                statement.setString(1, entity.getBoolRef());
                statement.setString(2, entity.getBookDate().toString());
                statement.setDouble(3, entity.getTotalAmount());
                statement.addBatch();
            }
            statement.executeBatch();
        });
    }

    @Override
    public List<Booking> getAll() throws SQLException {
        List<Booking> entities = new ArrayList<>();
        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select * from bookings");
            while (resultSet.next()) {
                entities.add(createEntity(resultSet));
            }
        });
        return entities;
    }

    private Booking createEntity(ResultSet set) throws SQLException {
        return new Booking(
                set.getString(1),
                ZonedDateTime.parse(set.getString(2)),
                set.getDouble(3)
        );
    }
}