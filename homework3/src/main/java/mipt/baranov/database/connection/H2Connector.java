package mipt.baranov.database.connection;

import lombok.NoArgsConstructor;
import mipt.baranov.database.JDBS.JdbcTemplate;
import mipt.baranov.database.connection.DBConnector;
import org.h2.jdbcx.JdbcConnectionPool;

@NoArgsConstructor
public class H2Connector implements DBConnector {

    @Override
    public JdbcTemplate connect() {
        return new JdbcTemplate(JdbcConnectionPool.create("jdbc:h2:mem:database;DB_CLOSE_DELAY=-1", "", ""));
    }

}
