package mipt.baranov.game.exeptions;

public class GameErrorExeption extends RuntimeException {
    GameErrorExeption(String message) {
        super(message);
    }
}
