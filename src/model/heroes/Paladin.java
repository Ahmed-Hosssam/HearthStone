package model.heroes;

import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.LevelUp;
import model.cards.spells.SealOfChampions;

import java.io.IOException;
import java.util.ArrayList;

public class Paladin extends Hero {
    public Paladin() throws IOException, CloneNotSupportedException {
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
        for (Card m : deck)
            if (m instanceof Minion)
                ((Minion) m).setListener(this);
    }
    public void useHeroPower() throws
            NotEnoughManaException,
            HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
            FullFieldException, CloneNotSupportedException{

        super.useHeroPower();
        Minion m = new Minion("Silver Hand Recruit",1,Rarity.BASIC,1,1,false,false,false);
        m.setListener(this);
        getField().add(m);
    }
}
