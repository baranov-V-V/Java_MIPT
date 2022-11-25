package mipt.baranov;

import mipt.baranov.database.AirtransH2Database;
import mipt.baranov.database.Database;

import java.io.IOException;
import java.sql.SQLException;

public class Server {
    private final Database database;
    private final BusinessLogic logic;

    public Server() {
        database = new AirtransH2Database();
        logic = new BusinessLogic(database);
    }

    public void run() {
        //do something
    }

    public void initialize() throws IOException, SQLException{
        database.initialize();
    }

    public void terminate() throws IOException, SQLException {
        database.terminate();
    }
}
