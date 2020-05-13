package exceptions;

import static Controller.GameController.playSound;

public class TauntBypassException extends  HearthstoneException{
    public TauntBypassException() {
        super("The opponent has taunt minion/s in his field and you need to kill it/them first.");
    }

    public TauntBypassException(String s) {
        super(s);
    }

    @Override
    public void playSoundExeption() {
        playSound("sounds/Class Heroes/Hunter/VO_HERO_05_ERROR11_31.wav");
    }
}
