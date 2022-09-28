package mipt.baranov;

import mipt.baranov.database.AirtransH2Database;
import mipt.baranov.database.dao.H2.AirportDao;
import mipt.baranov.database.dao.H2.FlightDao;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.List;
import java.util.Map;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws URISyntaxException, SQLException, IOException {
        //JSONObject obj = new JSONObject("{\"en\": \"Moscow\", \"ru\": \"Москва\"}");

        //System.out.println(obj.getString("ru"));

        AirtransH2Database database = new AirtransH2Database();
        database.initialize();

        database.execute(Paths.get("src/main/resources/database/queries/dao/H2/1.sql"));

        //AirportDao portDao = new AirportDao(database.getConnection());
        //Map<String, List<String>> cities = portDao.getCitiesWithManyAirports();

        FlightDao flightDao = new FlightDao(database.getConnection());
        //List<Map.Entry<String, Integer>> cities = flightDao.getMostCancelledCities();
        //List<Map.Entry<Month, Integer>> months = flightDao.getCancelledByMonth();
        String model = "763";
        flightDao.cancelFlightsByAirplaneModel(model);
        database.getConnection().executePreparedStatement("select status from flights\n" +
                    "where aircraft_code = ?", statement -> {
            statement.setString(1, model);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.printf("Status: %s", resultSet.getString(1));
            }
        });

        database.terminate();
        /*
        DBConnector connector = new H2Connector();
        JdbcTemplate jdbc = connector.connect();

        DBUtil.executeSqlFile(jdbc, Paths.get("src/main/resources/database/SetupLoadedH2DB.sql"));
        jdbc.executeStatement(statement -> {
            statement.execute("insert into boarding_passes values ('abc', 69, 420, 'a1')");
            statement.execute("insert into boarding_passes values ('abc', 69, 420, 'a2')");
        });
        System.out.println("done loading\n");
        /*
        jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select * from boarding_passes");
            while (resultSet.next()) {
                System.out.printf("%s %d %d %s\n", resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4));
            }
        });
        */
        /*
        jdbc.executeStatement(statement -> {
            ResultSet set = statement.executeQuery("select * from csvread('src/main/resources/database/airtrans/flights.csv', 'X1,X2,X3,X4,X5,X6,X7,X8,X9,X10', 'charset=UTF-8 fieldSeparator=,')");
            while (set.next()) {
                System.out.printf("%d %s\n", set.getInt(1), set.getString(2));
            }
        });
        */
    }
}
