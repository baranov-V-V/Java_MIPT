package mipt.baranov.game.input;

import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;


public class StepsInput implements Closeable {
    private final Scanner scanner;
    private List<String> black_steps;

    public StepsInput(InputStream input_stream, Charset charset) {
        scanner = new Scanner(input_stream, charset);
    }

    @Override
    public void close() {
        scanner.close();
    }

    public List<String> readAllSteps() {
        List<String> steps = new ArrayList<>();
        while (scanner.hasNext()) {
            steps.add(scanner.nextLine());
        }

        return steps;
    }
}
