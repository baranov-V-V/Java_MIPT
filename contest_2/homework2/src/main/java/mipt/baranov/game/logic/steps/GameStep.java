package mipt.baranov.game.logic.steps;

import mipt.baranov.game.util.Position;

public interface GameStep {
    Position getStartPos();

    Position getEndPos();
}
