package engine;

import exceptions.*;
import model.cards.Card;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;

import java.util.ArrayList;

public class Game implements ActionValidator, HeroListener {
    private Hero firstHero, secondHero, currentHero, opponent;

    public Game(Hero p1, Hero p2) {
        int rand = (int) (Math.random() * 2);
        firstHero = p1;
        secondHero = p2;
        if (rand == 0) {
            currentHero = p1;
            opponent = p2;
            p1.setCurrentManaCrystals(1);
            p1.setTotalManaCrystals(1);
        } else {
            currentHero = p2;
            p2.setCurrentManaCrystals(1);
            p2.setTotalManaCrystals(1);
            opponent = p1;
        }
    }

    public Hero getCurrentHero() {
        return currentHero;
    }

    public Hero getOpponent() {
        return opponent;
    }

    @Override
    public void validateTurn(Hero user) throws NotYourTurnException {
        if(user!=currentHero)
            throw new NotYourTurnException();
    }
    // always validate turn first
    @Override
    public void validateAttack(Minion attacker, Minion target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
        if(attacker.isSleeping() || attacker.isAttacked())
            throw new CannotAttackException();
        if(currentHero.getHand().contains(attacker))
            throw  new NotSummonedException();
        if(!target.isTaunt() && hasTaunt(opponent.getField()))
            throw new TauntBypassException();


    }
    public static boolean hasTaunt(ArrayList<Minion> field){
        for(Minion m : field)
            if(m.isTaunt())
                return true;
        return false;
    }

    @Override
    public void validateAttack(Minion attacker, Hero target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
        if(attacker.isSleeping() || attacker.isAttacked())
            throw new CannotAttackException();
        if(currentHero.getHand().contains(attacker))
            throw  new NotSummonedException();
        if(hasTaunt(opponent.getField()))
            throw new TauntBypassException();
        if(attacker instanceof Icehowl)
            throw new InvalidTargetException();
    }

    @Override
    public void validateManaCost(Card card) throws NotEnoughManaException {
        if(card.getManaCost()>currentHero.getCurrentManaCrystals())
            throw new NotEnoughManaException();
    }

    @Override
    public void validatePlayingMinion(Minion minion) throws FullFieldException {
        if(currentHero.getField().size()==7)
            throw new FullFieldException();
    }

    @Override
    public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException {
        if(hero.isHeroPowerUsed())
            throw new HeroPowerAlreadyUsedException();
        if(hero.getCurrentManaCrystals()<2)
            throw new NotEnoughManaException();
    }

    @Override
    public void onHeroDeath() {

    }

    @Override
    public void damageOpponent(int amount) {
        opponent.setCurrentHP(opponent.getCurrentHP()-amount);
    }

    @Override
    public void endTurn() throws FullHandException, CloneNotSupportedException {
        Hero temp = opponent;
        opponent = currentHero;
        currentHero = temp;
        currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals()+1);
        currentHero.setCurrentManaCrystals(currentHero.getTotalManaCrystals());
        currentHero.setHeroPowerUsed(false);
        for(Minion m : currentHero.getField()) {
            m.setAttacked(false);
            m.setSleeping(false);
        }
        drawCard();
    }
}
