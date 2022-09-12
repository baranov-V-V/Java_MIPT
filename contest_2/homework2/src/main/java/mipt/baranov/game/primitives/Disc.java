package mipt.baranov.game.primitives;

public class Disc {
    private Position position;
    private DiscType type;

    static public enum DiscType {
        KING, USUAL
    }

    public Disc(Position pos, DiscType init_type) {
        position = pos;
        type = init_type;
    }

    public Disc(Position pos) {
        position = pos;
        type = DiscType.USUAL;
    }

    public DiscType getType() {
        return type;
    }

    public void PromoteToKing() {
        type = DiscType.KING;
    }

    public void move(Position new_pos) {
        position = new_pos;
    }
}
