package exceptions;

public class FullFieldException extends HearthstoneException {
    public FullFieldException(){
        super("You field is full");
    }
    public FullFieldException(String s){
        super(s);
    }

}
