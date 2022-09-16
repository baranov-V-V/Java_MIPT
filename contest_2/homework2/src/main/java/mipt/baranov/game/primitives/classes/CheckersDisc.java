package mipt.baranov.game.primitives.classes;

import mipt.baranov.game.primitives.interfaces.BoardFigure;
import mipt.baranov.game.util.Boards;
import mipt.baranov.game.util.Color;

public class CheckersDisc implements BoardFigure {
    private DiscType type;
    private final Color color;
    private Position position;

    static public enum DiscType {
        KING, USUAL
    }

    public CheckersDisc(Position pos, DiscType init_type, Color init_color) {
        position = pos;
        type = init_type;
        color = init_color;
    }

    public CheckersDisc(String encoded_disc, Color init_color) {
        type = Character.isUpperCase(encoded_disc.charAt(0)) ? CheckersDisc.DiscType.KING : CheckersDisc.DiscType.USUAL;
        position = Boards.parsePosition(encoded_disc);
        color = init_color;
    }

    public DiscType getType() {
        return type;
    }

    public Color getColor() { return color; }

    public void PromoteToKing() {
        type = DiscType.KING;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
