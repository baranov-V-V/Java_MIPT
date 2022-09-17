package mipt.baranov.JDBS;

import lombok.AllArgsConstructor;

import javax.sql.DataSource;

@AllArgsConstructor
public class JdbcTemplate {
    private final DataSource connection_pool;


}
