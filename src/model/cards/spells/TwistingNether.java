package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

import java.util.ArrayList;

public class TwistingNether extends Spell implements AOESpell {
    public  TwistingNether(){
        super("Twisting Nether",8, Rarity.EPIC);
    }

    @Override
    public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
        oppField.clear();
        curField.clear();
    }
}
