package exceptions;

public class InvalidTargetException extends HearthstoneException {
    public InvalidTargetException() {
        super("You Can't select this Target");
    }

    public InvalidTargetException(String s) {
        super(s);
    }
}
