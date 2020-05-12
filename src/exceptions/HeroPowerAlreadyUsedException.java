package exceptions;

public class HeroPowerAlreadyUsedException extends HearthstoneException {

    public HeroPowerAlreadyUsedException() {
        super("You have already used the Hero Power");
    }

    public HeroPowerAlreadyUsedException(String s) {
        super(s);
    }
}
