package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

import java.util.ArrayList;

public class HolyNova extends Spell implements AOESpell{
    public HolyNova(){
        super("Holy Nova",5, Rarity.BASIC);
    }

    @Override
    public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
        ArrayList<Minion>temp = new ArrayList<>();

        for (Minion m:oppField)
            temp.add(m);
        for(Minion m : temp) {
            if(m.isDivine()) {
                m.setDivine(false);
            }else
            m.setCurrentHP(m.getCurrentHP() - 2);

        }
        for (Minion m:curField)
            m.setCurrentHP(m.getCurrentHP() +2);
    }
}
