package mipt.baranov.game.logic.classes;

import mipt.baranov.game.Game;
import mipt.baranov.game.logic.interfaces.GameStep;
import mipt.baranov.game.primitives.classes.Position;

public class CheckersGameStep implements GameStep {
    private final Position start;
    private final Position end;
    private final GameStepType type;

    public static enum GameStepType {
        MOVE, BEAT
    }

    CheckersGameStep(Position start_pos, Position end_pos, GameStepType step_type) {
        start = start_pos;
        end = end_pos;
        type = step_type;
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
