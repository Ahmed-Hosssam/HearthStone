package model.heroes;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.SiphonSoul;
import model.cards.spells.TwistingNether;

import java.io.IOException;
import java.util.ArrayList;

public class Warlock  extends Hero{
    public Warlock() throws IOException {
        super("Gul'dan");
        // ask about the ' character
    }

    @Override
    public void buildDeck() throws IOException {
        ArrayList<Minion> temp = getAllNeutralMinions("neutral_minions_31951 (2).csv");
        ArrayList<Minion> temp2 = getNeutralMinions(temp, 13);
        ArrayList<Card> deck = getDeck();
        for (Minion x : temp2)
            deck.add(x);
        deck.add(new CurseOfWeakness());
        deck.add(new CurseOfWeakness());
        deck.add(new SiphonSoul());
        deck.add(new SiphonSoul());
        deck.add(new TwistingNether());
        deck.add(new TwistingNether());
        deck.add(new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY, 4, 4, false, false, false));
        shuffle(deck);
    }
}
