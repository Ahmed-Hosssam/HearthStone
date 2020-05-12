package exceptions;

public class NotSummonedException extends HearthstoneException {
    public NotSummonedException() {
        super("This card is not summoned yet. (Not in the field)");
    }

    public NotSummonedException(String s) {
        super(s);
    }
}
