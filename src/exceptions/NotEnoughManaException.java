package exceptions;

public class NotEnoughManaException extends HearthstoneException {
    public NotEnoughManaException() {
        System.out.println("hshshshshsh");
    }

    public NotEnoughManaException(String s) {
        super(s);
    }
}
