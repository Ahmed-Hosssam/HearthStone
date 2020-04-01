package engine;

import model.heroes.Hero;
import model.heroes.HeroListener;

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
}
