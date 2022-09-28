package mipt.baranov.database.JDBS;

import lombok.AllArgsConstructor;
import mipt.baranov.util.sql.functional.ConnectionConsumer;
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
            connection.setAutoCommit(false);
            R result = function.apply(statement);
            connection.commit();
            return result;
        }
    }

    public void executeStatement(SqlConsumer<? super Statement> consumer) throws SQLException {
        try (Connection connection = connection_pool.getConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            consumer.accept(statement);
            connection.commit();
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

    public void workWithConnection(ConnectionConsumer<? super Connection> consumer) throws SQLException {
        try (Connection connection = connection_pool.getConnection();) {
            consumer.accept(connection);
        }
    }
}
