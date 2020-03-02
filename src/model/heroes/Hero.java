package model.heroes;
import model.cards.Card;
import model.cards.minions.Minion;
import java.util.ArrayList;

abstract public class Hero {
    private String name; // Read_Only
    private int currentHP; //The current health points of the hero. All heroes initially have 30 health point and can never exceed it.
    private boolean heroPowerUsed; // used their power during this turn
    private int totalManaCrystals; // number of mana crystal the hero starts the turn with , <=10
    private int currentManaCrystals; // equals to totalManaCrystals at the start of the turn, <=10
    private ArrayList<Card> deck; // Read Only
    private ArrayList<Minion> field; // Read Only
    private ArrayList<Card> hand; //Read Only

    public ArrayList<Card> getHand() {
        return hand;
    }

    private int fatigueDamage; // The damage a hero receives when trying to draw a card from an empty deck , NEITHER READ NOR WRITE.

    public String getName() {
        return name;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public boolean isHeroPowerUsed() {
        return heroPowerUsed;
    }

    public void setHeroPowerUsed(boolean heroPowerUsed) {
        this.heroPowerUsed = heroPowerUsed;
    }

    public int getTotalManaCrystals() {
        return totalManaCrystals;
    }

    public void setTotalManaCrystals(int totalManaCrystals) {
        this.totalManaCrystals = totalManaCrystals;
    }

    public int getCurrentManaCrystals() {
        return currentManaCrystals;
    }

    public void setCurrentManaCrystals(int currentManaCrystals) {
        this.currentManaCrystals = currentManaCrystals;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Minion> getField() {
        return field;
    }

    public Hero(String name){
        this.name = name;
        currentHP = 30;
        totalManaCrystals = 1;
        currentManaCrystals=1;
        // still has to build and deck
        // fatigue Damage = ??
    }



}
