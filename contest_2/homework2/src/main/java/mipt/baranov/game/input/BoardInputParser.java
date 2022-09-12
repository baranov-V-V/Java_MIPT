package mipt.baranov.game.input;

import mipt.baranov.game.primitives.Disc;
import mipt.baranov.game.primitives.Position;
import org.w3c.dom.html.HTMLIsIndexElement;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class BoardInputParser {
    public List<Disc> parseDiscs(int disc_num) {
        List<Disc> discs = new ArrayList<>();

        try (Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8)) {
            sc.useDelimiter(" ");
            for (int i = 0; i < disc_num; ++i) {
                discs.add(parseDisc(sc));
            }
        }

        return discs;
    }

    private Disc parseDisc(Scanner sc) {
        String encoded_disc = sc.next();
        return decodeDisc(encoded_disc);
    }

    private Disc decodeDisc(String encoded_disc) {
        String[] disc_components = encoded_disc.split("[a-zA-Z][0-9]");

        Disc.DiscType type = Disc.DiscType.USUAL;
        if (Character.isUpperCase(disc_components[0].charAt(0))){
            type = Disc.DiscType.KING;
        }

        return new Disc(new Position(Character.getNumericValue(
                        Character.toLowerCase(disc_components[0].charAt(0)) -
                        Character.getNumericValue('a')) + 1,
                        Integer.parseInt(disc_components[1])), type);
    }

}
