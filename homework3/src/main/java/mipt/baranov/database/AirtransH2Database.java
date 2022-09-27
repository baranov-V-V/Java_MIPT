package mipt.baranov.database;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AirtransH2Database extends H2Database {
    private final Logger dbLogger;

    public AirtransH2Database() {
        super();

        LogManager.getLogManager().addLogger(Logger.getLogger(H2Database.class.getName()));
        dbLogger = LogManager.getLogManager().getLogger(H2Database.class.getName());

        dbLogger.log(Level.FINE, "Constructed " + H2Database.class.getName());
    }

    @Override
    public void initialize() throws IOException, SQLException {
        execute(Paths.get("src/main/resources/database/SetupLoadedH2DB.sql"));
        dbLogger.log(Level.FINE, "Initialized " + H2Database.class.getName());
    };

    @Override
    public void terminate() throws IOException, SQLException {
        execute(Paths.get("src/main/resources/database/TerminateH2DB.sql"));
        dbLogger.log(Level.FINE, "Terminated " + H2Database.class.getName());
    };
}
