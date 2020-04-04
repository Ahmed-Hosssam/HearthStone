package model.heroes;

import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.DivineSpirit;
import model.cards.spells.HolyNova;
import model.cards.spells.ShadowWordDeath;

import java.io.IOException;
import java.util.ArrayList;

public class Priest extends Hero {
    public Priest() throws IOException, CloneNotSupportedException {
        super("Anduin Wrynn");
    }

    @Override
    public void buildDeck() throws IOException {
        ArrayList<Minion> temp = getAllNeutralMinions("neutral_minions_31951 (2).csv");
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
        for (Card m : deck)
            if (m instanceof Minion)
                ((Minion) m).setListener(this);
    }
    public void useHeroPower(Minion minion) throws
            NotEnoughManaException,
            HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
            FullFieldException, CloneNotSupportedException{
        super.useHeroPower();
        boolean f = true;
        for (Minion m:getField())
            if (m.getName().equals("Prophet Velen")){
                f = false;
                break;
            }
        if (f)
            minion.setCurrentHP(minion.getCurrentHP()+2);
        else
            minion.setCurrentHP(minion.getCurrentHP()+8);
    }
    public void useHeroPower(Hero hero) throws
            NotEnoughManaException,
            HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
            FullFieldException, CloneNotSupportedException{
        super.useHeroPower();
        hero.setCurrentHP(hero.getCurrentHP()+2);
    }
}
