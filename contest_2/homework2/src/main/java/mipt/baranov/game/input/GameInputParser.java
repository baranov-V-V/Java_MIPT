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

    public String[] readDiscLine() {
        return scanner.nextLine().split(" ");
    }
}
