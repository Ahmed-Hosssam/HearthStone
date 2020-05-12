package exceptions;

public class CannotAttackException extends HearthstoneException {
    public CannotAttackException(){
        super("You can NOT attack this card.");
    }
    public CannotAttackException(String s){

        super(s);
    }

}
