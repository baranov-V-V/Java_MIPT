package mipt.baranov.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface Dao<T> {
    void saveAll(Collection<T> entities) throws SQLException;

    List<T> getAll() throws SQLException;
}
