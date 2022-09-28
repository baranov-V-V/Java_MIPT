package mipt.baranov.database;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.*;

public class AirtransH2Database extends H2Database {
    private final Logger dbLogger;

    public AirtransH2Database() {
        super();

        LogManager.getLogManager().addLogger(Logger.getLogger(AirtransH2Database.class.getName()));
        dbLogger = LogManager.getLogManager().getLogger(AirtransH2Database.class.getName());

        dbLogger.setLevel(Level.ALL);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        dbLogger.addHandler(handler);
        dbLogger.log(Level.FINE, "Constructed " + AirtransH2Database.class.getName());
        //System.out.printf("constructor\n");
    }

    @Override
    public void initialize() throws IOException, SQLException {
        execute(Paths.get("src/main/resources/database/SetupLoadedH2DB.sql"));
        dbLogger.log(Level.FINE, "Initialized " + AirtransH2Database.class.getName());
    };

    @Override
    public void terminate() throws IOException, SQLException {
        execute(Paths.get("src/main/resources/database/TerminateH2DB.sql"));
        dbLogger.log(Level.FINE, "Terminated " + AirtransH2Database.class.getName());
    };
}
