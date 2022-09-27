package mipt.baranov.database.exeptions;

import java.io.IOException;

public class NonSqlFIleExeption extends IOException {
    public NonSqlFIleExeption(String message) {
        super(message);
    }
}
