package mipt.baranov.game.input;

import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

public class DiscsInput implements Closeable {
    private final Scanner scanner;

    public DiscsInput(InputStream input_stream, Charset charset) {
        scanner = new Scanner(input_stream, charset);
    }

    @Override
    public void close() {
        scanner.close();
    }

    public List<String> readDiscLine() {
        return List.of(scanner.nextLine().split(" "));
    }
}
