package mipt.baranov.game.core.classes;

import mipt.baranov.game.primitives.classes.CheckersDisc;
import mipt.baranov.game.primitives.classes.Position;

import java.util.*;

public class CheckersBoard implements mipt.baranov.game.core.interfaces.Board<CheckersDisc> {
    private final CheckersDisc[][] board;

    public CheckersBoard() {
        board = new CheckersDisc[8][8];
    }

    public CheckersBoard(Collection<? extends CheckersDisc> initial_discs) {
        board = new CheckersDisc[8][8];
        initial_discs.forEach(disc -> { board[disc.getPosition().getX()][disc.getPosition().getY()] = disc; });
    }

    @Override
    public void addFigure(CheckersDisc disc) {
        board[disc.getPosition().getX()][disc.getPosition().getY()] = disc;
    }

    @Override
    public void deleteFigure(Position pos) {
        board[pos.getX()][pos.getY()] = null;
    }

    @Override
    public boolean hasFigure(Position pos) {
        return getCheckersDisc(pos) != null;
    }

    @Override
    public CheckersDisc getFigure(Position pos) {
        return getCheckersDisc(pos);
    }

    @Override
    public void moveFigure(Position start, Position end) {
        CheckersDisc disc = getCheckersDisc(start);
        board[start.getX()][start.getY()] = null;
        disc.setPosition(end);
        board[end.getX()][end.getY()] = disc;
    }

    private CheckersDisc getCheckersDisc(Position pos) {
        return board[pos.getX()][pos.getY()];
    }
}
