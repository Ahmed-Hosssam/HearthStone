package exceptions;

import static Controller.GameController.playSound;

public class HeroPowerAlreadyUsedException extends HearthstoneException {

    public HeroPowerAlreadyUsedException() {
        super("You have already used the Hero Power");
    }

    public HeroPowerAlreadyUsedException(String s) {
        super(s);
    }

    @Override
    public void playSoundExeption() {
        playSound("sounds/Class Heroes/Mage/VO_HERO_08_ERROR12_86.wav");
    }
}
