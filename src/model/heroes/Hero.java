package model.heroes;

import engine.ActionValidator;
import exceptions.*;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.minions.MinionListener;
import model.cards.spells.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

abstract public class Hero implements MinionListener {
    private String name; // Read_Only
    private int currentHP; //The current health points of the hero. All heroes initially have 30 health point and can never exceed it.
    private boolean heroPowerUsed; // used their power during this turn
    private int totalManaCrystals; // number of mana crystal the hero starts the turn with , <=10
    private int currentManaCrystals; // equals to totalManaCrystals at the start of the turn, <=10
    private ArrayList<Card> deck; // Read Only
    private ArrayList<Minion> field; // Read Only
    private ArrayList<Card> hand; //Read Only
    private int fatigueDamage = 1; // The damage a hero receives when trying to draw a card from an empty deck , NEITHER READ NOR WRITE.
    private HeroListener listener;
    private ActionValidator validator;

    public ArrayList<Card> getHand() {
        return hand;
    }

    public final static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions, int count)  {
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
            try {
                out.add(minions.get(temp.get(rand)).clone());
            }catch (CloneNotSupportedException e){

            }


            lim--;
            temp.set(rand, temp.get(lim));
        }
        return out;
    }

    public void setValidator(ActionValidator validator) {
        this.validator = validator;
    }

    public HeroListener getListener() {
        return listener;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public Hero(String name) throws IOException, CloneNotSupportedException {
        this.name = name;
        currentHP = 30;
        deck = new ArrayList<Card>();
        field = new ArrayList<Minion>();
        hand = new ArrayList<Card>();
        buildDeck();
        Collections.shuffle(getDeck());
    }

    public static final ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException {
        Scanner sc = new Scanner(new File(filePath));
        ArrayList<Minion> out = new ArrayList<>();
        while (sc.hasNext()) {
            String[] in = sc.nextLine().split(",");
            String name = in[0];
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

    public void setListener(HeroListener listener) {
        this.listener = listener;
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
        this.totalManaCrystals = Math.max(Math.min(totalManaCrystals, 10), 0);
    }

    public int getCurrentManaCrystals() {
        return currentManaCrystals;
    }

    public void setCurrentManaCrystals(int currentManaCrystals) {
        this.currentManaCrystals = Math.max(Math.min(currentManaCrystals, 10), 0);

    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Minion> getField() {
        return field;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = Math.max(Math.min(currentHP, 30), 0);
        if (this.currentHP == 0)
            listener.onHeroDeath();
    }

    public void validate(Card s) throws NotYourTurnException, NotEnoughManaException {
        validator.validateTurn(this);
        validator.validateManaCost(s);
        getHand().remove(s);
    }

    public void castSpell(FieldSpell s) throws NotYourTurnException,
            NotEnoughManaException {
        validate((Card) s);
        if (this instanceof Mage)
            for (Minion m :field)
                if (m.getName().equals("Kalycgos")){
                    ((Card) s).setManaCost(((Card) s).getManaCost()-4);
                    break;
                }
        setCurrentManaCrystals(getCurrentManaCrystals()-((Card) s).getManaCost());
        s.performAction(getField());

    }

    public void castSpell(AOESpell s, ArrayList<Minion> oppField) throws
            NotYourTurnException, NotEnoughManaException {
        validate((Card) s);
        if (this instanceof Mage)
            for (Minion m :field)
                if (m.getName().equals("Kalycgos")){
                    ((Card) s).setManaCost(((Card) s).getManaCost()-4);
                    break;
                }
        setCurrentManaCrystals(getCurrentManaCrystals()-((Card) s).getManaCost());
        s.performAction(oppField, getField());
    }

    public void castSpell(MinionTargetSpell s, Minion m) throws NotYourTurnException,
            NotEnoughManaException, InvalidTargetException {
        validate((Card) s);
        if (this instanceof Mage)
            for (Minion mm :field)
                if (mm.getName().equals("Kalycgos")){
                    ((Card) s).setManaCost(((Card) s).getManaCost()-4);
                    break;
                }
        setCurrentManaCrystals(getCurrentManaCrystals()-((Card) s).getManaCost());
        s.performAction(m);
    }

    public void castSpell(HeroTargetSpell s, Hero h) throws NotYourTurnException,
            NotEnoughManaException {
        validate((Card) s);
        if (this instanceof Mage)
            for (Minion m :field)
                if (m.getName().equals("Kalycgos")){
                    ((Card) s).setManaCost(((Card) s).getManaCost()-4);
                    break;
                }
        setCurrentManaCrystals(getCurrentManaCrystals()-((Card) s).getManaCost());
        s.performAction(h);
    }

    public void castSpell(LeechingSpell s, Minion m) throws NotYourTurnException,
            NotEnoughManaException
    {
        validate((Card) s);
        if (this instanceof Mage)
            for (Minion mm :field)
                if (mm.getName().equals("Kalycgos")){
                    ((Card) s).setManaCost(((Card) s).getManaCost()-4);
                    break;
                }
        setCurrentManaCrystals(getCurrentManaCrystals()-((Card) s).getManaCost());
        setCurrentHP(s.performAction(m)+getCurrentHP());
    }

    public void endTurn() throws FullHandException, CloneNotSupportedException {
        listener.endTurn();
    }

    public Card drawCard() throws FullHandException, CloneNotSupportedException {


        Card drawn;
        if (deck.isEmpty()){
            setCurrentHP(getCurrentHP() - (fatigueDamage++));
            drawn = null;
        }
        else {
            drawn = deck.remove(0);
            if (hand.size() == 10)
                throw new FullHandException(drawn);
            hand.add(drawn);
            if (this instanceof Warlock && drawn instanceof Minion)
                for (Minion mm :field)
                    if (mm.getName().equals("Wilfred Fizzlebang")) {
                        drawn.setManaCost(0);
                        break;
                    }

            for (Minion m :field)
                if (m.getName().equals("Chromaggus")){
                    if (hand.size() < 10)
                        hand.add(drawn.clone());
                    break;
                }
        }

        return drawn;
    }

    public abstract void buildDeck() throws IOException, CloneNotSupportedException;
    public void useHeroPower() throws NotEnoughManaException,
            HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
            FullFieldException, CloneNotSupportedException{
        validator.validateTurn(this);
        validator.validateUsingHeroPower(this);
        if(this instanceof Paladin && getField().size()==7)
            throw new FullFieldException();
        setCurrentManaCrystals(getCurrentManaCrystals()-2);
        setHeroPowerUsed(true);
//        if(this instanceof Paladin)
//            validator.validatePlayingMinion(new Minion("Silver Hand Recruit",1,Rarity.BASIC,1,1,false,false,false));
    }
    public void playMinion(Minion m) throws NotYourTurnException,
            NotEnoughManaException, FullFieldException{
        validator.validateTurn(this);
        validator.validateManaCost(m);
        validator.validatePlayingMinion(m);
        setCurrentManaCrystals(getCurrentManaCrystals()-m.getManaCost());
        getHand().remove(m);
        getField().add(m);
    }
    public void attackWithMinion(Minion attacker, Minion target) throws
            CannotAttackException, NotYourTurnException, TauntBypassException,
            InvalidTargetException, NotSummonedException{
        validator.validateTurn(this);
        validator.validateAttack(attacker,target);

        attacker.attack(target);
    }
    public void attackWithMinion(Minion attacker, Hero target) throws
            CannotAttackException, NotYourTurnException, TauntBypassException,
            NotSummonedException, InvalidTargetException{
        validator.validateTurn(this);
        validator.validateAttack(attacker,target);
        attacker.attack(target);
    }

    @Override
    public void onMinionDeath(Minion m) {
        getField().remove(m);
    }
}
