package Controller;

import engine.Game;
import engine.GameListener;
import exceptions.*;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.*;
import view.GameView;
import view.HeroDeck;
import view.HeroPanel;
import view.MinionPanel;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameController implements GameListener, ActionListener {

    private Game model;
    private GameView view;
    private ArrayList<Hero> Heros;
    private Hero p1;
    private Hero p2;
    private ArrayList<JButton> herosButtons;

    private Card selected;
    static int c = 0;
    public GameController () {
        view = new GameView();

//        adding heros to Jpanel
        generateHeros();

        view.revalidate();
        view.repaint();
    }



    public void addingActionListener () {
        view.getOppoHeroPanel().getUseHeroPower().addActionListener(this);
        view.getCurHeroPanel().getUseHeroPower().addActionListener(this);
        view.getEndTurn().addActionListener(this);

    }

    public void generateHeros () {
        herosButtons = new ArrayList<>();
        herosButtons.add(new JButton("Hunter"));
        herosButtons.add(new JButton("Mage"));
        herosButtons.add(new JButton("Paladin"));
        herosButtons.add(new JButton("Priest"));
        herosButtons.add(new JButton("Warlock"));
        for (int i=0;i<herosButtons.size();i++) {
            JButton b = herosButtons.get(i);
            b.addActionListener(this);
            view.getHerosMenue().add(b);
        }
    }
     static void playSound(String soundFile) {
         AudioInputStream audioInputStream = null;
         try {
             audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
         } catch (UnsupportedAudioFileException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         Clip clip = null;
         try {
             clip = AudioSystem.getClip();
         } catch (LineUnavailableException e) {
             e.printStackTrace();
         }
         try {
             clip.open(audioInputStream);
         } catch (LineUnavailableException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         clip.start();
    }


    public Hero switchOnHeros (JButton b) {
        Hero p = null;
        switch (b.getActionCommand()) {
            case "Hunter":
                try {
                    p = new Hunter();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Mage":
                try {
                    p = new Mage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Paladin":
                try {
                    p = new Paladin();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Priest":
                try {
                    p = new Priest();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Warlock":
                try {
                    p = new Warlock();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                break;



        }
        return p;
    }



    @Override
    public void onGameOver() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
//        choosing players' heros:
        Hero p = null;
        if (!b.getActionCommand().equals("Exit")) {

            if (b.getActionCommand().equals("End Turn")) {
                try {
                    model.endTurn();
                } catch (FullHandException ex) {
                    ex.printStackTrace();
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                updateAll ();




            }

            if (b.getActionCommand().equals("select card")){

            }

            if (b.getActionCommand().equals("Attack")){
                    if (selected != null){

                    }

            }

            if (b.getActionCommand().equals("Use Hero Power")){

            }

            if (b.getActionCommand().equals("add to field")) {
                int idx = view.getButtons().indexOf(b);
                Card m = view.getCards().get(idx);
                try {
                    model.getCurrentHero().playMinion((Minion) m);
                } catch (NotYourTurnException ex) {
                    ex.printStackTrace();
                } catch (NotEnoughManaException ex) {
                    ex.printStackTrace();
                } catch (FullFieldException ex) {
                    ex.printStackTrace();
                }
                updateField(model.getCurrentHero(),view.getCurHeroField());
                updateHand(model.getCurrentHero(),view.getCurHeroHand());
            }

//            choosing from heros
            p = switchOnHeros(b);
            if (c == 0) {
                view.getHerosMenueLabel().setText("Second Player Hero:");
                p1 = p;

            } else if (c == 1) {
                p2 = p;
                try {
                    model = new Game(p1, p2);
                } catch (FullHandException ex) {
                    ex.printStackTrace();
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                view.createGamePlay(model.getCurrentHero().getName(),model.getOpponent().getName());
                addingActionListener();
                updateAll ();

//


            }
            c++;
        }

//        ......................

        view.pack();
        view.repaint();
        view.revalidate();
    }


    public void updateAll () {
        updateHeroPanel(view.getCurHeroPanel(),model.getCurrentHero());
        updateHeroPanel(view.getOppoHeroPanel(),model.getOpponent());
        updateHand(model.getCurrentHero(),view.getCurHeroHand());
        updateHand(model.getOpponent(),view.getOppoHeroHand());
        updateDeckPanel(view.getCurHeroDeck(),model.getCurrentHero());
        updateDeckPanel(view.getOppoHeroDeck(),model.getOpponent());
        updateField(model.getCurrentHero(),view.getCurHeroField());
        updateField(model.getOpponent(),view.getOppoHeroField());
    }


    public void updateHeroPanel (HeroPanel panel, Hero cur) {
        panel.getHeroInfo().setText("Name: " + cur.getName() + "\n" + "Current HP: " + cur.getCurrentHP() + "\n" + "Total mana crystals: " + cur.getTotalManaCrystals() + "\n" + "Current mana crystals: " + cur.getCurrentManaCrystals());
    }

    public void updateDeckPanel (HeroDeck panel, Hero cur) {
        panel.getCurHeroDeckInfo().setText("Cards left in your deck:" + "\n" + cur.getDeck().size());
    }


    public void updateField(Hero hero, JPanel panel) {
        panel.removeAll();
        ArrayList<Minion> handModel = hero.getField();
        ArrayList<JButton> buttons = view.getButtons();
        ArrayList<Card> cards = view.getCards();

        for(Card c : handModel){
            MinionPanel m = new MinionPanel(panel,"field",this);
            m.getMinionInfo().setText(c.toString());
            buttons.add(m.getSelectButton());
            cards.add(c);
        }
    }

    public void updateHand(Hero hero,JPanel panel){
        panel.removeAll();
        ArrayList<Card> handModel = hero.getHand();
        ArrayList<JButton>buttons = view.getButtons();
        ArrayList<Card> cards = view.getCards();
        for(Card c : handModel){
            MinionPanel m = new MinionPanel(panel,"hand",this);
            m.getMinionInfo().setText(c.toString());
            buttons.add(m.getSelectButton());
            cards.add(c);
        }
    }













    public static void main(String[] args) {
        new GameController();
        playSound("sounds/Background Music/Mulligan.wav");

    }
}
