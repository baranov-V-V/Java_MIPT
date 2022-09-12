package mipt.baranov.game.logic.interfaces;

import mipt.baranov.game.primitives.classes.Position;

public interface GameStep {
    Position getStartPos();

    Position getEndPos();
}
