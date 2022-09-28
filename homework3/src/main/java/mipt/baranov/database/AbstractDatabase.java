package mipt.baranov.database;

import mipt.baranov.database.JDBS.JdbcTemplate;
import mipt.baranov.database.exeptions.NonSqlFIleExeption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractDatabase implements Database {
    @Override
    public abstract void initialize() throws IOException, SQLException;

    @Override
    public abstract JdbcTemplate getConnection();

    @Override
    public abstract void execute(String sql) throws SQLException;

    @Override
    public void execute(Path sqlFile) throws IOException, SQLException {
        if (!sqlFile.toString().endsWith(".sql")) {
            throw new NonSqlFIleExeption(String.format("File %s expected to have .sql extension",
                                                        sqlFile.getFileName().toString()));
        }
        try (Stream<String> lines = Files.lines(sqlFile, StandardCharsets.UTF_8)) {
            final String sqlCommands = lines.collect(Collectors.joining("\n"));
            execute(sqlCommands);
        }
    };

    @Override
    public void execute(InputStream stream) throws IOException, SQLException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            execute(reader.lines().collect(Collectors.joining("\n")));
        }
    };

    @Override
    public abstract void terminate() throws IOException, SQLException;
}
