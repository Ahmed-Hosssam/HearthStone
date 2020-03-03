package model.heroes;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.DivineSpirit;
import model.cards.spells.HolyNova;
import model.cards.spells.ShadowWordDeath;

import java.io.IOException;
import java.util.ArrayList;

public class Priest extends Hero {
    public Priest() throws IOException {
        super("Anduin Wrynn");
    }

    @Override
    public void buildDeck() throws IOException {
        ArrayList<Minion> temp = getAllNeutralMinions("C:/Users/Ahmed hossam/IdeaProjects/Test/neutral_minions_31951 (2).csv");
        ArrayList<Minion> temp2 = getNeutralMinions(temp, 13);
        ArrayList<Card> deck = getDeck();
        for (Minion x : temp2)
            deck.add(x);
        deck.add(new DivineSpirit());
        deck.add(new DivineSpirit());
        deck.add(new HolyNova());
        deck.add(new HolyNova());
        deck.add(new ShadowWordDeath());
        deck.add(new ShadowWordDeath());
        deck.add(new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false));
        shuffle(deck);
    }
}
