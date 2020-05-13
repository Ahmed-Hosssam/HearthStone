package exceptions;

import static Controller.GameController.playSound;

public class FullFieldException extends HearthstoneException {
    public FullFieldException(){
        super("You field is full");
    }
    public FullFieldException(String s){
        super(s);
    }

    public void playSoundExeption (){
        playSound("sounds/Class Heroes/Mage/VO_HERO_08_ERROR07_81.wav");
    }
}
