package mipt.baranov.game.logic;

@FunctionalInterface
public interface ActionChecker {
    boolean is_valid_action();
}
