package mipt.baranov.game.core.interfaces;

import mipt.baranov.game.util.Position;
import mipt.baranov.game.entities.BoardFigure;

public interface Board<T extends BoardFigure> {
    void addFigure(T figure);

    void deleteFigure(Position position);

    boolean hasFigure(Position position);

    T getFigure(Position pos);

    void moveFigure(Position start, Position end);
}
