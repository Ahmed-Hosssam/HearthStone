package model.heroes;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.LevelUp;
import model.cards.spells.SealOfChampions;

import java.io.IOException;
import java.util.ArrayList;

public class Paladin extends Hero {
    public Paladin() throws IOException {
        super("Uther Lightbringer");
    }

    @Override
    public void buildDeck() throws IOException {
        ArrayList<Minion> temp = getAllNeutralMinions("neutral_minions_31951 (2).csv");
        ArrayList<Minion> temp2 = getNeutralMinions(temp, 15);
        ArrayList<Card> deck = getDeck();
        for (Minion x : temp2)
            deck.add(x);
        deck.add(new SealOfChampions());
        deck.add(new SealOfChampions());
        deck.add(new LevelUp());
        deck.add(new LevelUp());
        deck.add(new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true, false));
        shuffle(deck);
    }
}
