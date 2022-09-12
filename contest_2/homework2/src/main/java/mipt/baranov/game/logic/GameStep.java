package mipt.baranov.game.logic;

import mipt.baranov.game.primitives.Position;

public class GameStep {
    private GameStepType type;
    private Position start;
    private Position end;

    public GameStepType getType() {
        return type;
    };

    Position getStartPos() {
        return start;
    };

    Position getEndPos() {
        return end;
    };


}

enum GameStepType {
    MOVE, BEAT
}
