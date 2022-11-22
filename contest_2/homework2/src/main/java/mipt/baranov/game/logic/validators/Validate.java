package mipt.baranov.game.logic.validators;

import mipt.baranov.game.logic.steps.CheckersGameStep;

public abstract class AbstractValidate {
    abstract public boolean isValid(CheckersGameStep step);
}
