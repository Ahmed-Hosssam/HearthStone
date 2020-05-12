package exceptions;

public class TauntBypassException extends  HearthstoneException{
    public TauntBypassException() {
        super("The opponent has taunt minion/s in his field and you need to kill it/them first.");
    }

    public TauntBypassException(String s) {
        super(s);
    }
}
