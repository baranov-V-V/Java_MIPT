package mipt.baranov.database.dao.H2;

import mipt.baranov.database.Database;
import mipt.baranov.database.JDBS.JdbcTemplate;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class H2DaoTest implements DaoTest {
    protected final Database database;
    protected final JdbcTemplate jdbc;

    public H2DaoTest(Database db) throws SQLException {
        this.database = db;
        jdbc = database.getConnection();
    }

    @Override
    @BeforeAll
    public void setupDB() throws IOException, SQLException {
        database.initialize();
    }

    @Override
    @AfterAll
    public void tearDownDB() throws SQLException, IOException {
        database.terminate();
    }

    @Override
    @BeforeEach
    abstract public void initTables() throws SQLException;

    @Override
    @AfterEach
    public void dropTables() throws SQLException {
        jdbc.executeStatement(statement -> {
            statement.execute("drop all objects");
        });
    }

    @Override
    @Test
    abstract public void saveObjects() throws SQLException;

    @Override
    @Test
    abstract public void getObjects() throws SQLException;
}
