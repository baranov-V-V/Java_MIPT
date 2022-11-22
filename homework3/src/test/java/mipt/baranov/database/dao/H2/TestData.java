package mipt.baranov.database.dao.H2;

import mipt.baranov.entities.Aircraft;
import org.json.JSONObject;

public class TestData {
    public static class Aircrafts {
        public static final Aircraft BOENG = new Aircraft("773", "{\"en\": \"Boeing 777-300\", \"ru\": \"Боинг 777-300\"}", 11100);
        public static final Aircraft AIRBUS = new Aircraft("320", "{\"en\": \"Airbus A320-200\", \"ru\": \"Аэробус A320-200\"}", 5700);
        public static final Aircraft SUPERJET = new Aircraft("SU9", "{\"en\": \"Sukhoi Superjet-100\", \"ru\": \"Сухой Суперджет-100\"}", 3000);
    }
}
