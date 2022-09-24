package mipt.baranov.game.logic.steps;

import mipt.baranov.game.entities.CheckersDisc;
import mipt.baranov.game.util.Boards;
import mipt.baranov.game.util.Color;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StepsFactory {
    static public List<StepsHolder> constructStepHolders(List<String> encoded_step_lines) {
        List<StepsHolder> step_holders = new ArrayList<>();

        for (String step_line : encoded_step_lines) {
            String[] wb_steps = step_line.split(" ");
            step_holders.add(constructStepHolder(wb_steps[0], Color.WHITE));
            step_holders.add(constructStepHolder(wb_steps[1], Color.BLACK));
        }

        return step_holders;
    }

    static private StepsHolder constructStepHolder(String encoded_step, Color init_color) {
        String[] positions = encoded_step.split("[:-]");
        StepsHolder holder = new StepsHolder();
        CheckersGameStep.GameStepType type = encoded_step.matches(".{2}-.*") ?
                CheckersGameStep.GameStepType.MOVE:
                CheckersGameStep.GameStepType.BEAT;
        for (int i = 0; i < positions.length - 1; ++i) {
            holder.add(new CheckersGameStep(Boards.parsePosition(positions[i]),
                                            Boards.parsePosition(positions[i + 1]),
                                            type,
                                            init_color));
        }
        return new StepsHolder(new CheckersGameStep(encoded_step, init_color));
    }
}
