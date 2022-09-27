package mipt.baranov.database;

import mipt.baranov.database.JDBS.JdbcTemplate;
import mipt.baranov.database.connection.DBConnector;
import mipt.baranov.database.connection.H2Connector;
import mipt.baranov.util.sql.functional.SqlConsumer;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class H2Database extends AbstractDatabase {
    private final JdbcTemplate jdbc;
    private final Logger dbLogger;

    public H2Database() {
        DBConnector connector = new H2Connector();
        jdbc = connector.connect();

        LogManager.getLogManager().addLogger(Logger.getLogger(H2Database.class.getName()));
        dbLogger = LogManager.getLogManager().getLogger(H2Database.class.getName());
    }

    @Override
    public void initialize() throws IOException, SQLException {
        dbLogger.log(Level.INFO, "Calling of empty initialize() method");
    };

    @Override
    public JdbcTemplate getConnection() {
        return jdbc;
    };

    @Override
    public void execute(String sql) throws SQLException {
        jdbc.executeStatement((SqlConsumer<? super Statement>) statement -> statement.execute(sql));
    };

    @Override
    public void terminate() throws IOException, SQLException {
        dbLogger.log(Level.INFO, "Calling of empty terminate() method");
    };
}
