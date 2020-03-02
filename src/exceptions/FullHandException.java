package exceptions;

import model.cards.Card;

public class FullHandException extends HearthstoneException {
    private Card burned;

    public FullHandException(Card b){
        super();
        this.burned = b;
    }

    public FullHandException(String s, Card b){
        super(s);
        this.burned = b;
    }
}

