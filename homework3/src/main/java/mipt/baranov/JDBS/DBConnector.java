package mipt.baranov.JDBS;

@FunctionalInterface
public interface DBConnector {
    JdbcTemplate connect();
}
