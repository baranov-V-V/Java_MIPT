package mipt.baranov.game.primitives;

public class Position {
    private int coord_x = 0;
    private int coord_y = 0;

    public Position(int x, int y) {
        coord_x = x;
        coord_y = y;
    }

    public int getX() {
        return coord_x;
    }

    public int getY() {
        return coord_y;
    }
}
