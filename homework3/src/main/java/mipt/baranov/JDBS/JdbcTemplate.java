package mipt.baranov.JDBS;

import lombok.AllArgsConstructor;
import mipt.baranov.util.sql.functional.SqlConsumer;
import mipt.baranov.util.sql.functional.SqlFunction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@AllArgsConstructor
public class JdbcTemplate {
    private final DataSource connection_pool;

    public <R> R executeStatement(SqlFunction<? super Statement, ? extends R> function) throws SQLException {
        try (Connection connection = connection_pool.getConnection();
             Statement statement = connection.createStatement()) {
            return function.apply(statement);
        }
    }

    public void executeStatement(SqlConsumer<? super Statement> consumer) throws SQLException {
        try (Connection connection = connection_pool.getConnection();
             Statement statement = connection.createStatement()) {
            consumer.accept(statement);
        }
    }

    public <R> R executePreparedStatement(String sql_query, SqlFunction<? super PreparedStatement, ? extends R> function) throws SQLException {
        try (Connection connection = connection_pool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql_query)) {
            connection.setAutoCommit(false);
            R result = function.apply(stmt);
            connection.commit();
            return result;
        }
    }

    public void executePreparedStatement(String sql_query, SqlConsumer<? super PreparedStatement> consumer) throws SQLException {
        try (Connection connection = connection_pool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql_query)) {
            connection.setAutoCommit(false);
            consumer.accept(stmt);
            connection.commit();
        }
    }
}
