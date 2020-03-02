package exceptions;

public class CannotAttackException extends HearthstoneException {
    CannotAttackException(){
        super();
    }
    CannotAttackException(String s){
        super(s);
    }

}
