package mipt.baranov.game.entities;

import mipt.baranov.game.util.Boards;
import mipt.baranov.game.util.Color;

import java.util.ArrayList;
import java.util.List;

public class DiscFactory {
    static public List<CheckersDisc> constructDiscs(List<String> encoded_discs, Color discs_color) {
        List<CheckersDisc> discs = new ArrayList<>();
        encoded_discs.forEach(encoded -> discs.add(new CheckersDisc(encoded, discs_color)));
        return discs;
    }
}
