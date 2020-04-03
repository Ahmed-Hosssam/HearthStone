package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Polymorph extends Spell implements MinionTargetSpell {
    public Polymorph(){
        super("Polymorph",4, Rarity.BASIC);
    }


    @Override
    public void performAction(Minion m) throws InvalidTargetException {
        m.setCurrentHP(1);
        m.setAttack(1);
        m.setDivine(false);
        m.setMaxHP(1);
        m.setTaunt(false);
//        m.setSleeping(true); ask about this
        m.setManaCost(1);
        m.setName("Sheep");

    }
}
