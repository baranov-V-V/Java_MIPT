package mipt.baranov.database;

import mipt.baranov.database.JDBS.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.SQLException;

public interface Database {
    void initialize() throws IOException, SQLException;

    void execute(String sql) throws SQLException;

    void execute(Path sqlFile) throws IOException, SQLException;

    void execute(InputStream stream) throws IOException, SQLException;

    void terminate() throws IOException, SQLException;

    JdbcTemplate getConnection() throws SQLException;
}
