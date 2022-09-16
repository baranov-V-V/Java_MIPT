package mipt.baranov.game.input;

import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;


public class StepsInputParser implements Closeable {
    private final Scanner scanner;
    private List<String> black_steps;

    public StepsInputParser(InputStream input_stream, Charset charset) {
        scanner = new Scanner(input_stream, charset);
    }

    @Override
    public void close() {
        scanner.close();
    }

    public String[] readStepPair() {
        return scanner.nextLine().split(" ");
    }

    public List<String> readAllSteps() {
        List<String> steps = new ArrayList<>();
        while (scanner.hasNext()) {
            String[] encoded_steps = scanner.nextLine().split(" ");
            Collections.addAll(steps, encoded_steps);
        }

        return steps;
    }
}
