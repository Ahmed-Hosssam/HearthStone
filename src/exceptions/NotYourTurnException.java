package exceptions;

public class NotYourTurnException extends HearthstoneException {
    public NotYourTurnException() {

        super("It is NOT your turn.");
    }

    public NotYourTurnException(String s) {
        super(s);
    }
}
