package mipt.baranov.game.input;

import mipt.baranov.game.primitives.classes.CheckersDisc;
import mipt.baranov.game.primitives.classes.Position;

import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GameInputParser implements Closeable {
    private final Scanner scanner;

    public GameInputParser(InputStream input_stream, Charset charset) {
        scanner = new Scanner(input_stream, charset);
    }

    @Override
    public void close() {
        scanner.close();
    }

    public Map<Position, CheckersDisc> readDiscsLine(CheckersDisc.DiscColor line_color) {
        Map<Position, CheckersDisc> discs = new HashMap<>();

        try (Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8)) {
            String[] encoded_discs = sc.nextLine().split(" ");
            for(String encoded_disc: encoded_discs) {
                char[] arr = encoded_disc.toCharArray();
                discs.put(getPosition(arr), new CheckersDisc(getInitType(arr), line_color));
            }
        }

        return discs;
    }

    private CheckersDisc.DiscType getInitType(char[] arr) {
        return Character.isUpperCase(arr[0]) ? CheckersDisc.DiscType.KING : CheckersDisc.DiscType.USUAL;
    }

    private Position getPosition(char[] arr) {
        return new Position(Character.toLowerCase(arr[0]) - Character.getNumericValue('a') + 1, arr[1] - '0' + 1);
    }

}
