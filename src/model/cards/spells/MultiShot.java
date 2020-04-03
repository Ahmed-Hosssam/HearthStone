package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

import java.util.ArrayList;

public class MultiShot extends Spell implements AOESpell{
    public MultiShot(){
        super("Multi-Shot",4, Rarity.BASIC);
    }

    @Override
    public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
        int size = oppField.size();
        if(size>1){
            int first = (int)(Math.random()*size);
            int second = (int)(Math.random()*(size-1));
            if(second>=first)
                second++;
            Minion m1 = oppField.get(first);
            Minion m2 = oppField.get(second);
            m1.setCurrentHP(m1.getCurrentHP()-3);
            m2.setCurrentHP(m2.getCurrentHP()-3);
        }else if(size==1) {
            Minion m = oppField.get(0);
            m.setCurrentHP(m.getCurrentHP() - 3);
        }
    }
}
