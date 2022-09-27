package mipt.baranov.database;

import mipt.baranov.database.JDBS.JdbcTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DBUtil {
    /**
     * executes sql file
     * @param jdbc
     * @param path_to_file
     */
    public static void executeSqlFile(JdbcTemplate jdbc, Path path_to_file) throws IOException, SQLException {
        try (Stream<String> lines = Files.lines(path_to_file, StandardCharsets.UTF_8)) {
            final String sql_commands = lines.collect(Collectors.joining("\n"));
            jdbc.executeStatement(statement -> {statement.execute(sql_commands);} );
        }
        System.out.println("Working!");
    }
}
