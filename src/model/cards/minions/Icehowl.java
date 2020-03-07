package model.cards.minions;

import model.cards.Rarity;

import java.util.ArrayList;

//Can only attack minions not heros
public class Icehowl extends Minion {

    public Icehowl() {
        super("Icehowl", 9, Rarity.LEGENDARY, 10, 10, false, false, true);
    }

    @Override
    public Icehowl clone() {
        return new Icehowl();
    }


}
