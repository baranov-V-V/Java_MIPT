package mipt.baranov.dao.H2;

import lombok.AllArgsConstructor;
import mipt.baranov.JDBS.JdbcTemplate;
import mipt.baranov.dao.Dao;
import mipt.baranov.entities.Aircraft;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

@AllArgsConstructor
public class AircraftDao implements Dao<Aircraft> {
    private final JdbcTemplate jdbc;

    @Override
    public void saveAll(Collection<Aircraft> entities) throws SQLException {
        jdbc.executePreparedStatement("insert into aircrafts(aircraft_code, model, range) values (?, ?, ?)", statement -> {
            for (Aircraft entity : entities) {
                statement.setString(1, entity.getAircraftCode());
                statement.setString(2, entity.getModel().toString());
                statement.setInt(3, entity.getRange());
                statement.addBatch();
            }
            statement.executeBatch();
        });
    }

    @Override
    public List<Aircraft> getAll() throws SQLException {
        List<Aircraft> entities = new ArrayList<>();
        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select * from aircrafts");
            while (resultSet.next()) {
                entities.add(createEntity(resultSet));
            }
        });
        return entities;
    }

    private Aircraft createEntity(ResultSet set) throws SQLException {
        return new Aircraft(
                set.getString(1),
                new JSONObject(set.getString(2)),
                set.getInt(3)
        );
    }
}
