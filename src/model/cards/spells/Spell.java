package model.cards.spells;

import model.cards.Card;
import model.cards.Rarity;

abstract public class Spell extends Card {
    public Spell(String name, int manaCost, Rarity rarity) {
        super(name, manaCost, rarity);
    }
    public String toString(){
        return String.format("Spell%nName : %s%nMana Cost : %s%nRarity : %s",getName(),getManaCost(),getRarity());
    }

}
