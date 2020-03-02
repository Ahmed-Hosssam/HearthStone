package engine;

public class Game {
    private Hero firstHero, secondHero, currentHero, opponent;

    public Game(Hero p1, Hero p2) {
        int rand = (int) (Math.random() * 2);
        if (rand == 0) {
            firstHero = p1;
            secondHero = p2;
        } else {
            firstHero = p2;
            secondHero = p1;
        }
    }

    public Hero getCurrentHero() {
        return currentHero;
    }

    public Hero getOpponent() {
        return opponent;
    }
}
