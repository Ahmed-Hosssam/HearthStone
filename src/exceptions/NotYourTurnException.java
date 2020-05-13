package exceptions;

import static Controller.GameController.playSound;

public class NotYourTurnException extends HearthstoneException {
    public NotYourTurnException() {

        super("It is NOT your turn.");
    }

    public NotYourTurnException(String s) {
        super(s);
    }

    @Override
    public void playSoundExeption() {
        playSound("sounds/Class Heroes/Hunter/YouCanNotAttackThisCard.wav");
    }
}
