package exceptions;

import static Controller.GameController.playSound;

public class NotSummonedException extends HearthstoneException {
    public NotSummonedException() {
        super("This card is not summoned yet. (Not in the field)");
    }

    public NotSummonedException(String s) {
        super(s);
    }

    @Override
    public void playSoundExeption() {
//        playSound("sounds/Class Heroes/Mage/YouCanNotAttackThisCard.wav");
    }
}
