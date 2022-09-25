package mipt.baranov.util.sql.functional;

import java.sql.SQLException;

public interface SqlFunction<T, R> {
    R apply(T object) throws SQLException;
}
