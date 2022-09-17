package mipt.baranov.JDBS;

import lombok.NoArgsConstructor;
import org.h2.jdbcx.JdbcConnectionPool;

@NoArgsConstructor
public class H2Connector implements DBConnector {

    @Override
    public JdbcTemplate connect() {
        return new JdbcTemplate(JdbcConnectionPool.create("jdbc:h2:mem:database;DB_CLOSE_DELAY=-1", "", ""));
    }
}
