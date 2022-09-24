package mipt.baranov.game.logic.validators;

import mipt.baranov.game.logic.steps.CheckersGameStep;

public class ValidateBeat extends AbstractValidate {
    @Override
    public boolean isValid(CheckersGameStep step) {
        return false;
    }
}
