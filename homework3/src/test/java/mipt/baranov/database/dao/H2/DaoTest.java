package mipt.baranov.database.dao.H2;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface DaoTest {
    @BeforeAll
    void setupDB() throws IOException, SQLException;

    @AfterAll
    void tearDownDB() throws SQLException, IOException;

    @BeforeEach
    void initTables() throws SQLException;

    @AfterEach
    void dropTables() throws SQLException;

    /*
    private int getSpeakerCount() throws SQLException {
        return source.statement(stmt -> {
            ResultSet resultSet = stmt.executeQuery("select count(*) from speaker");
            resultSet.next();
            return resultSet.getInt(1);
        });
    }
    */

    /*
    private Collection<Speaker> getTestSpeakers() {
        return Arrays.asList(EGOROV, TOLKACHEV, BORISOV, VALEEV);
    }
    */

    @Test
    void saveObjects() throws SQLException;

    @Test
    void getObjects() throws SQLException;
}
