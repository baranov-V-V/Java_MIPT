package mipt.baranov.game.logic.classes;

import mipt.baranov.game.Game;
import mipt.baranov.game.logic.interfaces.GameStep;
import mipt.baranov.game.primitives.classes.Position;
import mipt.baranov.game.util.Boards;
import mipt.baranov.game.util.Color;

public class CheckersGameStep implements GameStep {
    private final Position start;
    private final Position end;
    private final GameStepType type;
    private final Color color;

    public static enum GameStepType {
        MOVE, BEAT
    }

    CheckersGameStep(Position start_pos, Position end_pos, GameStepType step_type, Color init_color) {
        color = init_color;
        start = start_pos;
        end = end_pos;
        type = step_type;
    }

    CheckersGameStep(String encoded_step, Color init_color) {
        color = init_color;
        if (encoded_step.matches(".{2}-.*")) {
            type = GameStepType.MOVE;
        }
        else {
            type = GameStepType.BEAT;
        }

        String[] lines = encoded_step.split("[:-]");
        start = Boards.parsePosition(lines[0]);
        end = Boards.parsePosition(lines[1]);
    }

    public GameStepType getType() {
        return type;
    };

    @Override
    public Position getStartPos() {
        return start;
    };

    @Override
    public Position getEndPos() {
        return end;
    };

}
