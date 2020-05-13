package exceptions;

import Controller.GameController;

import static Controller.GameController.playSound;

public class CannotAttackException extends HearthstoneException {
    public CannotAttackException(){
        super("You can NOT attack this card. The minion is sleeping.");
    }
    public CannotAttackException(String s){
        super(s);
    }

//    public void playSoundExeption (){
//        playSound("sounds/Class Heroes/Mage/YouCanNotAttackThisCard.wav");
//    }

}
