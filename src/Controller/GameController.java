package Controller;

import engine.Game;
import engine.GameListener;
import exceptions.*;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.*;
import model.heroes.*;
import view.GameView;
import view.HeroDeck;
import view.HeroPanel;
import view.MinionPanel;
import view.Window;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class GameController implements GameListener, ActionListener {

    private Game model;
    private GameView view;
    private ArrayList<Hero> Heros;
    private Hero p1;
    private Hero p2;
    private ArrayList<JButton> herosButtons;
    private JFrame GameOver;
    private Object selected;
    public static ArrayList<JButton> buttons = new ArrayList<>();
    public static ArrayList<Object> cards = new ArrayList<>();
    static int c = 0;
    private boolean useHeroPower = false;
    private Spell castSpell;

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
           Font font = new Font("SERIF", Font.BOLD, 16);
            //b.setBackground(Color.darkGray);
            //b.setForeground(Color.lightGray);
            b.setFont(font);
            b.addActionListener(this);
            view.getHerosMenue().add(b);
        }
    }
     public static void playSound(String soundFile) {
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
        playSound("sounds/Class Heroes/Warlock/VO_HERO_07_Death_17.wav");
        String winner = model.getCurrentHero().getCurrentHP() > 0 ?model.getCurrentHero().getName():model.getOpponent().getName();
        GameOver = new JFrame();
        GameOver.setSize(400,100);
        GameOver.setDefaultCloseOperation(view.EXIT_ON_CLOSE);
        GameOver.setLocationRelativeTo(null);
        GameOver.setTitle("HearthStone");
        GameOver.setResizable(false);
        GameOver.setMinimumSize(new Dimension(GameOver.getWidth(), GameOver.getHeight()));
        GameOver.setMinimumSize(new Dimension(GameOver.getWidth(), GameOver.getHeight()));
        GameOver.add(new JLabel("Game Over, " + winner + " wins!!"),BorderLayout.CENTER);
        GameOver.pack();
        view.dispose();
        GameOver.setVisible(true);
        GameOver.revalidate();
        GameOver.repaint();

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
                    ex.playSoundExeption();
                    new Window(ex.getMessage());
                } catch (CloneNotSupportedException ex) {
                    new Window(ex.getMessage());
                }
                castSpell = null;
                String temp = view.getCurHeroPanel().getHeroName().getText();
                view.getCurHeroPanel().getHeroName().setText(view.getOppoHeroPanel().getHeroName().getText());
                view.getOppoHeroPanel().getHeroName().setText(temp);


                view.getCurHeroPanel().getUseHeroPower().setBackground(null);
                useHeroPower = false;
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
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (HeroPowerAlreadyUsedException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (NotYourTurnException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (FullHandException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (FullFieldException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (CloneNotSupportedException ex) {
                                new Window(ex.getMessage());
                            }
                        }
                        else {
                            try {
                                ((Mage) model.getCurrentHero()).useHeroPower(selected.equals("currentHero")?model.getCurrentHero():model.getOpponent());
                            } catch (NotEnoughManaException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (HeroPowerAlreadyUsedException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (NotYourTurnException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (FullHandException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (FullFieldException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (CloneNotSupportedException ex) {
                                new Window(ex.getMessage());
                            }
                        }

                    }

                    else if (model.getCurrentHero() instanceof Priest){
                        if (selected instanceof Minion) {
                            try {
                                ((Priest) model.getCurrentHero()).useHeroPower((Minion) selected);
                            } catch (NotEnoughManaException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (HeroPowerAlreadyUsedException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (NotYourTurnException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (FullHandException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (FullFieldException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (CloneNotSupportedException ex) {
                                new Window(ex.getMessage());
                            }
                        }
                        else {
                            try {
                                ((Priest) model.getCurrentHero()).useHeroPower(selected.equals("currentHero")?model.getCurrentHero():model.getOpponent());
                            } catch (NotEnoughManaException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (HeroPowerAlreadyUsedException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (NotYourTurnException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (FullHandException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (FullFieldException ex) {
                                ex.playSoundExeption();
                                new Window(ex.getMessage());
                            } catch (CloneNotSupportedException ex) {
                                new Window(ex.getMessage());
                            }
                        }
                    }


                }
                else if (castSpell != null){
                    try {
                        if (castSpell instanceof HeroTargetSpell)
                            model.getCurrentHero().castSpell((HeroTargetSpell) castSpell,selected.equals("currentHero")?model.getCurrentHero():model.getOpponent());
                        else if (castSpell instanceof MinionTargetSpell)
                            model.getCurrentHero().castSpell((MinionTargetSpell) castSpell,(Minion) selected);
                        else if (castSpell instanceof LeechingSpell)
                            model.getCurrentHero().castSpell((LeechingSpell) castSpell,(Minion) selected);
                    }
                    catch (NotYourTurnException ex) {
                        ex.playSoundExeption();
                        new Window(ex.getMessage());
                    } catch (NotEnoughManaException ex) {
                        ex.playSoundExeption();
                        new Window(ex.getMessage());
                    } catch (InvalidTargetException ex) {
                        ex.playSoundExeption();
                        new Window(ex.getMessage());
                    }
                }
                castSpell = null;
                view.getCurHeroPanel().getUseHeroPower().setBackground(null);
                useHeroPower = false;
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
                            new Window(ex.getMessage());
                            ex.playSoundExeption();
                        } catch (NotYourTurnException ex) {
                            ex.playSoundExeption();
                            new Window(ex.getMessage());
                        } catch (TauntBypassException ex) {
                            ex.playSoundExeption();
                            new Window(ex.getMessage());
                        } catch (InvalidTargetException ex) {
                            ex.playSoundExeption();
                            new Window(ex.getMessage());
                        } catch (NotSummonedException ex) {
                            ex.playSoundExeption();
                            new Window(ex.getMessage());
                        }
                    }
                view.getCurHeroPanel().getUseHeroPower().setBackground(null);
                useHeroPower = false;
            }

            if (b.getActionCommand().equals("Use Hero Power")){

                if (useHeroPower){
//                        view.getCurHeroPanel().getUseHeroPower().setBackground(null);
//                        useHeroPower = false;
                }else {
                    if (model.getCurrentHero() instanceof Mage || model.getCurrentHero() instanceof Priest) {
                        b.setBackground(Color.GREEN);
                        useHeroPower = true;
                    } else {
                        try {
                            model.getCurrentHero().useHeroPower();
                        } catch (NotEnoughManaException ex) {
                            ex.playSoundExeption();
                            new Window(ex.getMessage());
                        } catch (HeroPowerAlreadyUsedException ex) {
                            ex.playSoundExeption();
                            new Window(ex.getMessage());
                        } catch (NotYourTurnException ex) {
                            ex.playSoundExeption();
                            new Window(ex.getMessage());
                        } catch (FullHandException ex) {
                            ex.playSoundExeption();
                            new Window(ex.getMessage());
                        } catch (FullFieldException ex) {
                            ex.playSoundExeption();
                            new Window(ex.getMessage());
                        } catch (CloneNotSupportedException ex) {
                            new Window(ex.getMessage());
                        }
                    }
                }
            }
            if (b.getActionCommand().equals("add to field")) {
                int idx = buttons.indexOf(b);
                Card m = (Card) cards.get(idx);
                try {
                    model.getCurrentHero().playMinion((Minion) m);
                } catch (NotYourTurnException ex) {
                    ex.playSoundExeption();
                    new Window(ex.getMessage());
                } catch (NotEnoughManaException ex) {
                    ex.playSoundExeption();
                    new Window(ex.getMessage());
                } catch (FullFieldException ex) {
                    ex.playSoundExeption();
                    new Window(ex.getMessage());
                }
                view.getCurHeroPanel().getUseHeroPower().setBackground(null);
                useHeroPower = false;
            }
            if (b.getActionCommand().equals("cast")) {
                int idx = buttons.indexOf(b);
                Spell s = (Spell) cards.get(idx);
                try {
                    if (s instanceof AOESpell)
                            model.getCurrentHero().castSpell((AOESpell) s, model.getOpponent().getField());
                    else if (s instanceof FieldSpell)
                        model.getCurrentHero().castSpell((FieldSpell) s);
                    else if (s instanceof HeroTargetSpell || s instanceof LeechingSpell || s instanceof MinionTargetSpell)
                        castSpell = s;


                }
                catch (NotYourTurnException ex) {
                    ex.playSoundExeption();
                    new Window(ex.getMessage());
                } catch (NotEnoughManaException ex) {
                    ex.playSoundExeption();
                    new Window(ex.getMessage());
                }
                view.getCurHeroPanel().getUseHeroPower().setBackground(null);
                useHeroPower = false;
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
                    ex.playSoundExeption();
                    new Window(ex.getMessage());
                } catch (CloneNotSupportedException ex) {
                    new Window(ex.getMessage());
                }
                model.setListener(this);
                view.createGamePlay(model.getCurrentHero().getName(),model.getOpponent().getName());
                if (p1 == model.getCurrentHero()){
                    view.getCurHeroPanel().getHeroName().setText("Player 1");
                    view.getOppoHeroPanel().getHeroName().setText("Player 2");
                }
                else {
                    view.getCurHeroPanel().getHeroName().setText("Player 2");
                    view.getOppoHeroPanel().getHeroName().setText("Player 1");
                }
                addingActionListener();
                updateAll ();

            }
            c++;


        }

//        ......................
        if (c > 2)
            updateAll ();

        view.pack();
        view.repaint();
        view.revalidate();
    }


    public void updateAll () {
        updateHeroPanel(view.getCurHeroPanel(),model.getCurrentHero());
        setHeroPowersHoveringText(model.getCurrentHero(),view.getCurHeroPanel());
        updateHeroPanel(view.getOppoHeroPanel(),model.getOpponent());
        updateHand(model.getCurrentHero(),view.getCurHeroHand());
        updateHand(model.getOpponent(),view.getOppoHeroHand());
        updateDeckPanel(view.getCurHeroDeck(),model.getCurrentHero());
        updateDeckPanel(view.getOppoHeroDeck(),model.getOpponent());
        updateField(model.getCurrentHero(),view.getCurHeroField(),true);
        updateField(model.getOpponent(),view.getOppoHeroField(),false);
    }

    public static void setHeroPowersHoveringText (Hero s, HeroPanel p) {
        if (s instanceof Mage)
            setHoveringText(p.getUseHeroPower(),"Inflict one damage point to a specific target. Please select a minion or a hero after pressing use Hero power.");
        else if (s instanceof Priest)
            setHoveringText(p.getUseHeroPower(),"Restore two health points to a specific target. Please select a minion or a hero after pressing use Hero power.");
        else if (s instanceof Hunter)
            setHoveringText(p.getUseHeroPower(),"Inflict two damage points to the opponent hero.");
        else if (s instanceof Warlock)
            setHoveringText(p.getUseHeroPower(),"Draw an extra card and inflict two damage points to the hero.");
        else
            setHoveringText(p.getUseHeroPower(),"Create a new minion with the following attributes and add it to the field:\n" +
                    "1- Its currentHP, maxHp and attack value (all with a value of 1).\n" +
                    "2- Its name is \"Silver Hand Recruit\".\n" +
                    "3- Its rarity is BASIC.\n" +
                    "4- It is a non-taunt, non-divine and non-charge minion.\n" +
                    "5- It costs 1 mana crystal.");

    }



        public void updateHeroPanel (HeroPanel panel, Hero cur) {

        panel.getHeroInfo().setText("Name: " + cur.getName() + "\n" + "Current HP: " + cur.getCurrentHP() + "\n" + "Total mana crystals: " + cur.getTotalManaCrystals() + "\n" + "Current mana crystals: " + cur.getCurrentManaCrystals());

    }

    public void updateDeckPanel (HeroDeck panel, Hero cur) {

        panel.getCurHeroDeckInfo().setText("Cards left in your deck:" + "\n" + cur.getDeck().size());
    }
    public void updateField(Hero hero, JPanel panel,boolean f) {
        panel.removeAll();
        ArrayList<Minion> handModel = hero.getField();

        for(Card c : handModel){
            MinionPanel m = new MinionPanel(panel,"field","minion",this,f);
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
            if(hero==model.getCurrentHero()) {
                MinionPanel m = new MinionPanel(panel, "hand", c instanceof Spell ? "spell" : "minion", this, false);
                m.getMinionInfo().setText(c.toString());
                buttons.add(m.getSelectButton());
                cards.add(c);
                if (c instanceof Spell)
                    setSpellsHoveringText((Spell) c, m);
            }else{
                ImageIcon icon = new ImageIcon("images/cardback.png");
                JLabel backOfCard = new JLabel();
                backOfCard.setIcon(icon);

                panel.add(backOfCard);
            }
        }


    }


public static void setSpellsHoveringText (Spell s, MinionPanel p) {
        if (s instanceof CurseOfWeakness)
            setHoveringText(p.getSelectButton(),"Decreases the attack of all enemy minions by 2.");
        else if (s instanceof DivineSpirit)
            setHoveringText(p.getSelectButton(),"Doubles the current and max HP of a minion." + "\n" + " Please select a minion after pressing cast.");
        else if (s instanceof Flamestrike)
            setHoveringText(p.getSelectButton(),"Deals 4 damage to all enemy minions.");
        else if (s instanceof HolyNova)
            setHoveringText(p.getSelectButton(),"Deals 2 damage to all enemy minions.");
        else if (s instanceof KillCommand)
            setHoveringText(p.getSelectButton(),"Deals 5 damage to a minion or 3 damage to a hero." + "\n" + " Please select a minion or a hero after pressing cast.");
        else if (s instanceof LevelUp)
            setHoveringText(p.getSelectButton(),"Increase the attack, current, and max HP of all silver hand recruits by 1.");
        else if (s instanceof MultiShot)
            setHoveringText(p.getSelectButton(),"Deals 3 damage to two random enemy minions. If the opponent has only one minion,\n" +
                    "it deals 3 damage once to this minion. If the opponent’s field is empty then nothing happens.");
        else if (s instanceof Polymorph)
            setHoveringText(p.getSelectButton(),"Transforms a minion into a minion with the following attributes:\n" +
                    "1- CurrentHP, maxHp and attack value (all with a value of 1).\n" +
                    "2- Name is \"Sheep\".\n" +
                    "3- A non-taunt, non-divine and non-charge minion.\n" +
                    "4- Mana cost is 1 mana crystal.");
        else if (s instanceof Pyroblast)
            setHoveringText(p.getSelectButton(),"Deals 10 damage to a chosen target  to a hero or a minion." + "\n" + " Please select a minion or a hero after pressing cast.");
        else if (s instanceof SealOfChampions)
            setHoveringText(p.getSelectButton(),"Increases the attack of a minion by 3 and gives it divine shield." + "\n" + " Please select a minion after pressing cast.");
        else if (s instanceof SiphonSoul)
            setHoveringText(p.getSelectButton(),"Destroys a minion even if it has a divine shield and restores 3 health points to the hero." + "\n" + " Please select a minion after pressing cast.");
        else if (s instanceof ShadowWordDeath)
            setHoveringText(p.getSelectButton(),"Destroys a minion that his attack is 5 or more even if it has a divine shield." + "\n" + " Please select a minion after pressing cast.");

        else
            setHoveringText(p.getSelectButton(),"Destroys all minions of both heroes even if any of them has a divine shield.");
}

public static void setHoveringText(JButton b,String text) {
    b.addMouseListener(new MouseAdapter() {

        public void mouseEntered(MouseEvent mEvt) {
            b.setToolTipText(text);

        }

    });
}




    public static void main(String[] args) {
        new GameController();
        playSound("sounds/Legendary Cards/Card Sound Effects/Battle_Play_Stinger_3.wav");

    }
}
