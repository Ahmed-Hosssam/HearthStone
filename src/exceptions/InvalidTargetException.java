package exceptions;

import static Controller.GameController.playSound;

public class InvalidTargetException extends HearthstoneException {
    public InvalidTargetException() {
        super("You Can't select this Target");
    }

    public InvalidTargetException(String s) {
        super(s);
    }

    @Override
    public void playSoundExeption() {
        playSound("sounds/Class Heroes/Mage/VO_HERO_08_ERROR10_84.wav");
    }
}
