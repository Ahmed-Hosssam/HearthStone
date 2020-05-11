package Controller;

import engine.Game;
import engine.GameListener;
import exceptions.*;
import model.cards.Card;
import model.heroes.*;
import view.GameView;
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
                view.intializeHeroPanel(model.getCurrentHero(),this);

            }

            if (b.getActionCommand().equals("select card")){
                if (view.getCurFieldButtons().contains(b)) {
                    int idx = view.getCurFieldButtons().indexOf(b);
//                    selected = view.getCurFieldCards()
                }
                else {

                }
            }

            if (b.getActionCommand().equals("Attack")){
                    if (selected != null){

                    }

            }

            if (b.getActionCommand().equals("Use Hero Power")){
                try {
                    model.getCurrentHero().useHeroPower();
                } catch (NotEnoughManaException ex) {
                    ex.printStackTrace();
                } catch (HeroPowerAlreadyUsedException ex) {
                    ex.printStackTrace();
                } catch (NotYourTurnException ex) {
                    ex.printStackTrace();
                } catch (FullHandException ex) {
                    ex.printStackTrace();
                } catch (FullFieldException ex) {
                    ex.printStackTrace();
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
               // view.intializeFirstTurn(model.getCurrentHero(),model.getOpponent(),this);

            }

            if (b.getActionCommand().equals("add to field")) {
                JButton attack = new JButton("Attack");
                if (view.getCurHandButtons().contains(b)){
                    int idx = view.getCurHandButtons().indexOf(b);
                    try {
                        model.getCurrentHero().validate(view.getCurHandCards().get(idx));
                    } catch (NotYourTurnException ex) {
                        ex.printStackTrace();
                    } catch (NotEnoughManaException ex) {
                        ex.printStackTrace();
                    }
                    MinionPanel m = view.getCurHandPanels().get(idx);
                    view.getCurHeroHand().remove(m);
                    view.getCurHeroField().add(m);
                    m.setAttackButton(attack,this);
                }
                else {
                    int idx = view.getOppoHandButtons().indexOf(b);
                    try {
                        model.getCurrentHero().validate(view.getOppoHandCards().get(idx));
                    } catch (NotYourTurnException ex) {
                        ex.printStackTrace();
                    } catch (NotEnoughManaException ex) {
                        ex.printStackTrace();
                    }
                    MinionPanel m = view.getOppoHandPanels().get(idx);
                    view.getOppoHeroHand().remove(m);
                    view.getOppoHeroField().add(m);
                    m.setAttackButton(attack,this);
                }
                b.setText("select card");
                view.pack();
                view.revalidate();
                view.repaint();
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
                view.intializeFirstTurn(model.getCurrentHero(),model.getOpponent(),this);
//                view.intializeHand(this);


            }
            c++;
        }

//        ......................



    }

    public static void main(String[] args) {
        new GameController();
        playSound("sounds/Background Music/Mulligan.wav");

    }
}
