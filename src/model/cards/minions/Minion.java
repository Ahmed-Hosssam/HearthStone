package model.cards.minions;

import exceptions.InvalidTargetException;
import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;

public class Minion extends Card implements Cloneable {
    private int attack;
    private int maxHP;
    private int currentHP;
    private boolean taunt;
    private boolean divine;
    private boolean sleeping;
    private boolean attacked;
    private MinionListener listener;

    public Minion(String name, int manaCost, Rarity rarity, int attack, int maxHP, boolean taunt, boolean divine, boolean charge) {
        super(name, manaCost, rarity);
        this.attack = Math.max(attack, 0);
        this.maxHP = Math.max(maxHP, 0);
        this.currentHP = this.maxHP;
        this.taunt = taunt;
        this.divine = divine;
        this.sleeping = !charge;
        this.attacked = false;
    }
    public String  toString (){
        return String.format("Minion%nName : %s%nMana Cost : %s%nRarity : %s%nAttack : %s%nHP : %s//%s%nTaunt : %s%nDivine Shield : %s%nSleeping : %s",getName(),getManaCost(),getRarity(),attack,currentHP,maxHP,taunt?"YES":"NO",divine?"YES":"NO",sleeping?"YES":"NO");
    }


    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack < 0 ? 0 : attack;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = Math.max(maxHP, 0);
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP > getMaxHP() ? getMaxHP() : currentHP < 0 ? 0 : currentHP;
        if(this.currentHP==0)
            listener.onMinionDeath(this);
    }

    public boolean isTaunt() {
        return taunt;
    }

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public boolean isDivine() {
        return divine;
    }

    public void setDivine(boolean divine) {
        this.divine = divine;
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public void setListener(MinionListener listener) {
        this.listener = listener;
    }

    @Override
    public Minion clone() throws CloneNotSupportedException {
        return (Minion) super.clone();
    }

    public void attack(Minion target) {
        attacked=true;
        helperAttack(target);
        target.helperAttack(this);
    }

    public void helperAttack(Minion target) {
        if(getAttack()==0)
            return;
        if (target.isDivine()) {
            target.setDivine(false);
        } else {
            target.setCurrentHP(target.getCurrentHP() - this.getAttack());
        }
    }

    public void attack(Hero target) throws InvalidTargetException {
        if (this instanceof Icehowl)
            throw new InvalidTargetException();
        attacked=true;
        target.setCurrentHP(target.getCurrentHP() - getAttack());
    }
}
