package mipt.baranov.game.logic.steps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StepsHolder implements Iterable<CheckersGameStep> {
    private final List<CheckersGameStep> plain_steps;

    public void add(CheckersGameStep step) {
        plain_steps.add(step);
    }

    public StepsHolder() {
        plain_steps = new ArrayList<>();
    }
    public StepsHolder(Collection<? extends CheckersGameStep> steps) {
        plain_steps = new ArrayList<>();
        plain_steps.addAll(steps);
    }

    public StepsHolder(CheckersGameStep step) {
        plain_steps = new ArrayList<>();
        plain_steps.add(step);
    }

    @Override
    public Iterator<CheckersGameStep> iterator() {
        return plain_steps.iterator();
    };
}
