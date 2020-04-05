package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.minions.MinionListener;

import java.util.ArrayList;

public class Flamestrike extends Spell implements AOESpell {

    public Flamestrike(){
        super("Flamestrike",7, Rarity.BASIC);
    }

    @Override
    public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
        ArrayList<Minion> temp = new ArrayList<Minion>();
        
        for (Minion m:oppField)
            temp.add(m);
        for(Minion m : temp)
            if(m.isDivine())
                m.setDivine(false);
            else
                m.setCurrentHP(m.getCurrentHP() - 4);


    }
}
