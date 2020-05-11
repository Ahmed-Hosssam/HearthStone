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
    public void updateCurHand(){
        ArrayList<Card> handModel = model.getCurrentHero().getHand();
        ArrayList<MinionPanel> handView = new ArrayList<>();
        ArrayList<JButton> buttons = new ArrayList<>();
        for(Card c : handModel){
            MinionPanel m = new MinionPanel(view.getCurHeroPanel());

        }
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
//                view.intializeHeroPanel(model.getCurrentHero(),this);

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
//                view.intializeFirstTurn(model.getCurrentHero(),model.getOpponent(),this);
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
