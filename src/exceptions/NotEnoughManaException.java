package exceptions;

public class NotEnoughManaException extends HearthstoneException {
    public NotEnoughManaException() {
        super("You don't have enough mana crystals.");
    }

    public NotEnoughManaException(String s) {
        super(s);
    }
}
