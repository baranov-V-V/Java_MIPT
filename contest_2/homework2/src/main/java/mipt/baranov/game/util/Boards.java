package mipt.baranov.game.util;

public class Boards {
    static public Position parsePosition(String encoded_pos) {
        return new Position(Character.toLowerCase(encoded_pos.charAt(0)) -
                            Character.getNumericValue('a') + 1,
                            encoded_pos.charAt(1) - '0' + 1);
    }
}
