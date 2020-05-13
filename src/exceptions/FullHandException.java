package exceptions;

import model.cards.Card;

import static Controller.GameController.playSound;

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

    @Override
    public void playSoundExeption() {
        playSound("sounds/Class Heroes/Hunter/VO_HERO_05_ERROR06_26.wav");
    }
}

