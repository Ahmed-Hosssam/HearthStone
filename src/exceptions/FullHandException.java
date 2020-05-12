package exceptions;

import model.cards.Card;

public class FullHandException extends HearthstoneException {
    private Card burned;

    public FullHandException(Card b){
        super("Your hand is full and the drawn card is burned" + "\n" + b);
        this.burned = b;

    }

    public FullHandException(String s, Card b){
        super(s);
        this.burned = b;
    }
}

