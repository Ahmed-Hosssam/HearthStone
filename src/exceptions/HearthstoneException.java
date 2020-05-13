package exceptions;


import static Controller.GameController.playSound;

abstract public class HearthstoneException extends Exception {
    public HearthstoneException(){
        super();
    }
    public HearthstoneException(String s){
        super(s);
    }
    public abstract void playSoundExeption ();
}
