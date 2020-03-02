package exceptions;

import model.cards.Card;

public class FullHandException extends HearthstoneException {
    private Card burned;

    FullHandException(Card b){
        super();
        this.burned = b;
    }

    FullHandException(String s, Card b){
        super(s);
        this.burned = b;
    }
}

