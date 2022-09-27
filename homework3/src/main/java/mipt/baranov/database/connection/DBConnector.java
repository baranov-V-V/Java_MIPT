package mipt.baranov.database.connection;

import mipt.baranov.database.JDBS.JdbcTemplate;

@FunctionalInterface
public interface DBConnector {
    JdbcTemplate connect();
}
