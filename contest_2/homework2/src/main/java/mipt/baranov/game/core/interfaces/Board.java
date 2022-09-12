package mipt.baranov.game.core.interfaces;

import mipt.baranov.game.primitives.classes.Position;
import mipt.baranov.game.primitives.interfaces.BoardFigure;

public interface Board<T extends BoardFigure> {
    void addFigure(Position pos, T figure);

    void deleteFigure(Position position);

    boolean hasFigure(Position position);

    T getFigure(Position pos);

    void moveFigure(Position start, Position end);
}
