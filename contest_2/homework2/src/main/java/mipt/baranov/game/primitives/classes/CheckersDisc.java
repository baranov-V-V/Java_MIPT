package mipt.baranov.game.primitives.classes;

import mipt.baranov.game.primitives.interfaces.BoardFigure;

public class CheckersDisc implements BoardFigure {
    private DiscType type;
    private final DiscColor color;

    static public enum DiscType {
        KING, USUAL
    }

    static public enum DiscColor {
        WHITE, BLACK
    }

    public CheckersDisc(DiscType init_type, DiscColor init_color) {
        type = init_type;
        color = init_color;
    }

    public DiscType getType() {
        return type;
    }

    public DiscColor getColor() { return color; }

    public void PromoteToKing() {
        type = DiscType.KING;
    }
}
