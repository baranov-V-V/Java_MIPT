package mipt.baranov.util.sql.H2;

import mipt.baranov.util.Point;

import java.sql.Types;
import java.util.Locale;

public final class Converters {
    public static String pointToString(Point point) {
        return String.format(Locale.UK, "POINT(%1$d %2$d)", point.getX(), point.getY());
    }

    /*
    public static Point stringToPoint(String point) {
        return new Point()
        return String.format(Locale.UK, "POINT(%1$d %2$d)", point.getX(), point.getY());
    }
    */

    public static String getJsonString(String H2JsonString) {
        return H2JsonString.substring(1, H2JsonString.length() - 1).replace("\\\"", "\"");
    }

    private Converters() {}
}
