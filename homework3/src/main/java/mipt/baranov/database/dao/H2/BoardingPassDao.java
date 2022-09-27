package mipt.baranov.database.dao.H2;

import lombok.AllArgsConstructor;
import mipt.baranov.database.JDBS.JdbcTemplate;
import mipt.baranov.database.dao.Dao;
import mipt.baranov.entities.BoardingPass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class BoardingPassDao implements Dao<BoardingPass> {
    private final JdbcTemplate jdbc;

    @Override
    public void saveAll(Collection<BoardingPass> entities) throws SQLException {
        jdbc.executePreparedStatement("insert into boarding_passes(ticket_no, flight_id, boarding_no, seat_no) values (?, ?, ?, ?)", statement -> {
            for (BoardingPass entity : entities) {
                statement.setString(1, entity.getTicketNo());
                statement.setInt(2, entity.getFlightId());
                statement.setInt(3, entity.getBoardingNo());
                statement.setString(4, entity.getSeatNo());
                statement.addBatch();
            }
            statement.executeBatch();
        });
    }

    @Override
    public List<BoardingPass> getAll() throws SQLException {
        List<BoardingPass> entities = new ArrayList<>();
        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select * from boarding_passes");
            while (resultSet.next()) {
                entities.add(createEntity(resultSet));
            }
        });
        return entities;
    }

    private BoardingPass createEntity(ResultSet set) throws SQLException {
        return new BoardingPass(
                set.getString(1),
                set.getInt(2),
                set.getInt(3),
                set.getString(4)
        );
    }
}
