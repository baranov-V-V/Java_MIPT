package mipt.baranov.database.dao.H2;

import lombok.AllArgsConstructor;
import mipt.baranov.database.JDBS.JdbcTemplate;
import mipt.baranov.database.dao.Dao;
import mipt.baranov.entities.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class SeatDao implements Dao<Seat> {
    private final JdbcTemplate jdbc;

    @Override
    public void saveAll(Collection<Seat> entities) throws SQLException {
        jdbc.executePreparedStatement("insert into seats(aircraft_code, seat_no, fare_conditions) values (?, ?, ?)", statement -> {
            for (Seat entity : entities) {
                statement.setString(1, entity.getAircraftCode());
                statement.setString(2, entity.getSeatNo());
                statement.setString(3, entity.getFareCondition().getStringType());
                statement.addBatch();
            }
            statement.executeBatch();
        });
    }

    @Override
    public List<Seat> getAll() throws SQLException {
        List<Seat> entities = new ArrayList<>();
        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select * from seats");
            while (resultSet.next()) {
                entities.add(createEntity(resultSet));
            }
        });
        return entities;
    }

    private Seat createEntity(ResultSet set) throws SQLException {
        return new Seat(
                set.getString(1),
                set.getString(2),
                Seat.SeatType.valueOf(set.getString(3))
        );
    }
}