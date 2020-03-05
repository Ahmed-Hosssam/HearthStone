package model.heroes;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

abstract public class Hero {
    private String name; // Read_Only
    private int currentHP; //The current health points of the hero. All heroes initially have 30 health point and can never exceed it.
    private boolean heroPowerUsed; // used their power during this turn
    private int totalManaCrystals; // number of mana crystal the hero starts the turn with , <=10
    private int currentManaCrystals; // equals to totalManaCrystals at the start of the turn, <=10
    private ArrayList<Card> deck; // Read Only
    private ArrayList<Minion> field; // Read Only
    private ArrayList<Card> hand; //Read Only
    private int fatigueDamage; // The damage a hero receives when trying to draw a card from an empty deck , NEITHER READ NOR WRITE.

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public Hero(String name) throws IOException {
        this.name = name;
        currentHP = 30;
        deck = new ArrayList<Card>();
        field = new ArrayList<Minion>();
        hand = new ArrayList<Card>();
        buildDeck();
    }

    public static final ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException {
        Scanner sc = new Scanner(new File(filePath));
        ArrayList<Minion> out = new ArrayList<>();
        HashSet<String> hs = new HashSet<>();
        while (sc.hasNext()) {
            String[] in = sc.nextLine().split(",");
            String name = in[0];
            if (hs.contains(name))
                continue;
            hs.add(name);

            if (name.equals("Icehowl")) {
                out.add(new Icehowl());
                continue;
            }
            int manaCost = Integer.parseInt(in[1]);
            char r = in[2].charAt(0);
            Rarity rarity = r == 'b' ? Rarity.BASIC : r == 'c' ? Rarity.COMMON : r == 'r' ? Rarity.RARE : r == 'e' ? Rarity.EPIC : Rarity.LEGENDARY;
            int attack = Integer.parseInt(in[3]);
            int maxhp = Integer.parseInt(in[4]);
            boolean taunt = in[5].equals("TRUE");
            boolean divine = in[6].equals("TRUE");
            boolean charge = in[7].equals("TRUE");
            Minion m = new Minion(name, manaCost, rarity, attack, maxhp, taunt, divine, charge);
            out.add(m);
        }
        return out;
    }

    public final static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions, int count) {
        ArrayList<Minion> out = new ArrayList<Minion>();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i < minions.size(); i++) {
            Minion m = minions.get(i);
            if (m.getRarity() == Rarity.LEGENDARY) {
                temp.add(i);
            } else {
                temp.add(i);
                temp.add(i);
            }
        }
        int lim = temp.size();
        while (count-- > 0) {
            int rand = (int) (Math.random() * lim);
            out.add(minions.get(temp.get(rand)).clone());
            lim--;
            temp.set(rand, temp.get(lim));
        }
        return out;
    }

    public final static void shuffle(ArrayList list) {
        for (int i = list.size(); i > 0; i--) {
            int rand = (int) (Math.random() * i);
            Object temp = list.get(i - 1);
            list.set(i - 1, list.get(rand));
            list.set(rand, temp);
        }
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
        this.totalManaCrystals = Math.min(totalManaCrystals, 10);
    }

    public int getCurrentManaCrystals() {
        return currentManaCrystals;
    }

    public void setCurrentManaCrystals(int currentManaCrystals) {
        this.currentManaCrystals = Math.min(currentManaCrystals, 10);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Minion> getField() {
        return field;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = Math.min(currentHP, 30);
    }

    public abstract void buildDeck() throws IOException;


}
