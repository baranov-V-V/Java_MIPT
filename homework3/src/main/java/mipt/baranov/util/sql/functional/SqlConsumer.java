package mipt.baranov.util.sql.functional;

import java.sql.SQLException;

@FunctionalInterface
public interface SqlConsumer<T> {
    void accept(T object) throws SQLException;
}
