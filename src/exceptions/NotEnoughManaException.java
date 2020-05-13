package exceptions;

import static Controller.GameController.playSound;

public class NotEnoughManaException extends HearthstoneException {
    public NotEnoughManaException() {
        super("You don't have enough mana crystals.");
    }

    public NotEnoughManaException(String s) {
        super(s);
    }

    @Override
    public void playSoundExeption() {
        playSound("sounds/Class Heroes/Hunter/VO_HERO_05_ERROR02_22.wav");
    }
}
