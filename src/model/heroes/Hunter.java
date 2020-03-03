package model.heroes;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.KillCommand;
import model.cards.spells.MultiShot;

import java.io.IOException;
import java.util.ArrayList;

public class Hunter extends Hero {
    public Hunter() throws IOException {
        super("Rexxar");
    }

    @Override
    public void buildDeck() throws IOException {
        ArrayList<Minion> temp = getAllNeutralMinions("C:/Users/Ahmed hossam/IdeaProjects/Test/neutral_minions_31951 (2).csv");
        ArrayList<Minion> temp2 = getNeutralMinions(temp, 15);
        ArrayList<Card> deck = getDeck();
        for (Minion x : temp2)
            deck.add(x);
        deck.add(new KillCommand());
        deck.add(new KillCommand());
        deck.add(new MultiShot());
        deck.add(new MultiShot());
        deck.add(new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false, false, true));
        shuffle(deck);
    }
}
