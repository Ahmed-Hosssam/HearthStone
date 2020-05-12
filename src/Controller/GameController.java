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

    private Object selected;
    public static ArrayList<JButton> buttons = new ArrayList<>();
    public static ArrayList<Object> cards = new ArrayList<>();
    static int c = 0,turnCounter;
    private boolean useHeroPower = false;

    public GameController () {
        view = new GameView();
        view.setListener(this);
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
                useHeroPower = false;
                try {
                    model.endTurn();
                    turnCounter++;
                } catch (FullHandException ex) {
                    ex.printStackTrace();
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
            }
            if (b.getActionCommand().equals("select")){
                int idx = buttons.indexOf(b);
                selected = cards.get(idx);
                if (useHeroPower) {
                    if (model.getCurrentHero() instanceof Mage){
                        if (selected instanceof Minion) {
                            try {
                                ((Mage) model.getCurrentHero()).useHeroPower((Minion) selected);
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
                        }
                        else {
                            try {
                                ((Mage) model.getCurrentHero()).useHeroPower(selected.equals("currentHero")?model.getCurrentHero():model.getOpponent());
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
                        }

                    }

                    else if (model.getCurrentHero() instanceof Priest){
                        if (selected instanceof Minion) {
                            try {
                                ((Priest) model.getCurrentHero()).useHeroPower((Minion) selected);
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
                        }
                        else {
                            try {
                                ((Priest) model.getCurrentHero()).useHeroPower(selected.equals("currentHero")?model.getCurrentHero():model.getOpponent());
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
                        }
                    }
                    useHeroPower = false;
                }

            }

            if (b.getActionCommand().equals("Attack")){
                int idx = buttons.indexOf(b);
                Object attacked = cards.get(idx);

                    if (selected != null){
                        try {
                            if (attacked instanceof Minion)
                                model.getCurrentHero().attackWithMinion((Minion) selected ,(Minion) attacked);
                            else
                                model.getCurrentHero().attackWithMinion((Minion) selected ,model.getOpponent());
                        } catch (CannotAttackException ex) {
                            ex.printStackTrace();
                        } catch (NotYourTurnException ex) {
                            ex.printStackTrace();
                        } catch (TauntBypassException ex) {
                            ex.printStackTrace();
                        } catch (InvalidTargetException ex) {
                            ex.printStackTrace();
                        } catch (NotSummonedException ex) {
                            ex.printStackTrace();
                        }
                    }

            }

            if (b.getActionCommand().equals("Use Hero Power")){
                int idx = buttons.indexOf(b);
                Object attacked = cards.get(idx);

                if (model.getCurrentHero() instanceof Mage || model.getCurrentHero() instanceof Priest)
                    useHeroPower = true;
                else {
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
                }
            }
            if (b.getActionCommand().equals("add to field")) {
                int idx = buttons.indexOf(b);
                Card m = (Card) cards.get(idx);
                try {
                    model.getCurrentHero().playMinion((Minion) m);
                } catch (NotYourTurnException ex) {
                    ex.printStackTrace();
                } catch (NotEnoughManaException ex) {
                    ex.printStackTrace();
                } catch (FullFieldException ex) {
                    ex.printStackTrace();
                }

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

            }
            c++;
        }
        if (c > 2)
            updateAll ();

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
        updateField(model.getCurrentHero(),view.getCurHeroField(),true);
        updateField(model.getOpponent(),view.getOppoHeroField(),false);
    }
    public void updateHeroPanel (HeroPanel panel, Hero cur) {
        String bottom = turnCounter%2==0?"First Player":"Second Player";
        String top =    turnCounter%2==1?"First Player":"Second Player";
        panel.getHeroName().setText(cur==model.getCurrentHero()?top:bottom);
        panel.getHeroInfo().setText("Name: " + cur.getName() + "\n" + "Current HP: " + cur.getCurrentHP() + "\n" + "Total mana crystals: " + cur.getTotalManaCrystals() + "\n" + "Current mana crystals: " + cur.getCurrentManaCrystals());
    }
    public void updateDeckPanel (HeroDeck panel, Hero cur) {
        panel.getCurHeroDeckInfo().setText("Cards left in your deck:" + "\n" + cur.getDeck().size());
    }
    public void updateField(Hero hero, JPanel panel,boolean f) {
        panel.removeAll();
        ArrayList<Minion> handModel = hero.getField();


        for(Card c : handModel){
            MinionPanel m = new MinionPanel(panel,"field",this,f);
            if (!f)
                cards.add(c);
            m.getMinionInfo().setText(c.toString());
            buttons.add(m.getSelectButton());
            cards.add(c);
        }
    }
    public void updateHand(Hero hero,JPanel panel){
        panel.removeAll();
        ArrayList<Card> handModel = hero.getHand();

        for(Card c : handModel){
            MinionPanel m = new MinionPanel(panel,"hand",this,false);

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