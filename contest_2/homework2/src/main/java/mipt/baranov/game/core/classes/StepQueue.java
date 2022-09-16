package mipt.baranov.game.core.classes;

import mipt.baranov.game.logic.classes.CheckersGameStep;
import mipt.baranov.game.logic.interfaces.GameStep;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public class StepQueue {
    final Queue<CheckersGameStep> queue;

    public StepQueue(Collection<? extends CheckersGameStep> steps) {
        queue = new ArrayDeque<>();
        queue.addAll(steps);
    }

    CheckersGameStep poll() {
        return queue.poll();
    }

    void add(CheckersGameStep step) {
        queue.add(step);
    }
}
