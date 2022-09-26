package mipt.baranov;

import mipt.baranov.JDBS.DBConnector;
import mipt.baranov.JDBS.DBUtil;
import mipt.baranov.JDBS.H2Connector;
import mipt.baranov.JDBS.JdbcTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws URISyntaxException, SQLException, IOException {
        DBConnector connector = new H2Connector();
        JdbcTemplate jdbc = connector.connect();
        DBUtil.executeSqlFile(jdbc, Paths.get("src/main/resources/database/SetupLoadedH2DB.sql"));
        jdbc.executeStatement(statement -> {
            statement.execute("insert into boarding_passes values ('abc', 69, 420, 'a1')");
            statement.execute("insert into boarding_passes values ('abc', 69, 420, 'a2')");
        });
        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select * from boarding_passes");
            while (resultSet.next()) {
                System.out.printf("%s %d %d %s\n", resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4));
            }
        });
    }
}
