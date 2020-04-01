package model.heroes;

import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.Flamestrike;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;

import java.io.IOException;
import java.util.ArrayList;

public class Mage extends Hero {
    public Mage() throws IOException {
        super("Jaina Proudmoore");
    }

    @Override
    public void buildDeck() throws IOException {
        ArrayList<Minion> temp = getAllNeutralMinions("neutral_minions_31951 (2).csv");
        ArrayList<Minion> temp2 = getNeutralMinions(temp, 13);
        ArrayList<Card> deck = getDeck();
        for (Minion x : temp2)
            deck.add(x);
        deck.add(new Polymorph());
        deck.add(new Polymorph());
        deck.add(new Flamestrike());
        deck.add(new Flamestrike());
        deck.add(new Pyroblast());
        deck.add(new Pyroblast());
        deck.add(new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false, false, false));

    }
    public void useHeroPower(Minion minion) throws
            NotEnoughManaException,
            HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
            FullFieldException, CloneNotSupportedException{
        super.useHeroPower();
        minion.setCurrentHP(minion.getCurrentHP()-1);
    }
    public void useHeroPower(Hero hero) throws
            NotEnoughManaException,
            HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
            FullFieldException, CloneNotSupportedException{
        super.useHeroPower();
        hero.setCurrentHP(hero.getCurrentHP()-1);
    }
}
