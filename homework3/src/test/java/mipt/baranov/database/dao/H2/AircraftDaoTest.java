package mipt.baranov.database.dao.H2;

import mipt.baranov.database.H2Database;
import mipt.baranov.entities.Aircraft;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static mipt.baranov.database.dao.H2.TestData.Aircrafts.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AircraftDaoTest extends H2DaoTest {
    private final AircraftDao dao;

    public AircraftDaoTest() throws SQLException {
        super(new H2Database());
        dao = new AircraftDao(jdbc);
    }

    @Override
    @BeforeAll
    public void setupDB() throws IOException, SQLException {
        super.setupDB();
    }

    @Override
    @AfterAll
    public void tearDownDB() throws SQLException, IOException {
        super.tearDownDB();
    }

    @Override
    @BeforeEach
    public void initTables() throws SQLException {
        jdbc.executeStatement(statement -> {
            statement.execute("create table aircrafts (\n" +
                              "    aircraft_code VARCHAR(3) not null,\n" +
                              "    model         JSON       not null,\n" +
                              "    range         INTEGER    not null \n);");
        });
    }

    @Override
    @AfterEach
    public void dropTables() throws SQLException {
        super.dropTables();
    }

    @Override
    @Test
    public void saveObjects() throws SQLException {
        Collection<Aircraft> testObjects = getTestObjects();
        assertEquals(0, getObjectsCount());
        dao.saveAll(testObjects);
        assertEquals(testObjects.size(), getObjectsCount());
    }

    @Override
    @Test
    public void getObjects() throws SQLException {
        Collection<Aircraft> testObjects = getTestObjects();
        dao.saveAll(testObjects);
        List<Aircraft> aircrafts = dao.getAll();
        assertNotNull(aircrafts);
        assertEquals(new HashSet<>(aircrafts), new HashSet<>(testObjects));
        //assertTrue(CollectionUtils.isEqualCollection(aircrafts, testObjects));
    }

    private Collection<Aircraft> getTestObjects() {
        return Arrays.asList(BOENG, AIRBUS, SUPERJET);
    }

    private int getObjectsCount() throws SQLException {
        return jdbc.executeStatement(statement -> {
            ResultSet resultSet = statement.executeQuery("select count(*) from aircrafts");
            resultSet.next();
            return resultSet.getInt(1);
        });
    }
}
