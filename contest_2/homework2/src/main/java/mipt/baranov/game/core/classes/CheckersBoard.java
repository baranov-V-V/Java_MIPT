package mipt.baranov.game.core.classes;

import mipt.baranov.game.primitives.classes.CheckersDisc;
import mipt.baranov.game.primitives.classes.Position;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CheckersBoard implements mipt.baranov.game.core.interfaces.Board<CheckersDisc> {
    private final Map<Position, CheckersDisc> discs;

    public CheckersBoard() {
        discs = new HashMap<>();
    }

    public CheckersBoard(Map<Position, CheckersDisc> initial_discs) {
        discs = new HashMap<>();
        discs.putAll(initial_discs);
    }

    @Override
    public void addFigure(Position pos, CheckersDisc disc) {
        discs.put(pos, disc);
    }

    @Override
    public void deleteFigure(Position pos) {
        discs.remove(pos);
    }

    @Override
    public boolean hasFigure(Position pos) {
        return discs.containsKey(pos);
    }

    @Override
    public CheckersDisc getFigure(Position pos) {
        return discs.get(pos);
    }

    @Override
    public void moveFigure(Position start, Position end) {

    }

}
