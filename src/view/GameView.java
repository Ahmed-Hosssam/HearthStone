package view;


import Controller.GameController;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GameView extends JFrame {

    public JPanel getHerosMenue() {
        return herosMenue;
    }

    private JPanel herosMenue;
    private JLabel herosMenueLabel = new JLabel("First Player Hero:");
    private Insets insets;
    private Dimension size;
    private JPanel currPanel;
    private JPanel oppoPanel;
    private JButton exit;
    private JButton endTurn;
    private boolean f = true;
    private HeroPanel curHeroPanel;
    private HeroDeck curHeroDeck;
    private FieldViewPanel curHeroField;
    private FieldViewPanel curHeroHand;
    private FieldViewPanel oppoHeroHand;
    private FieldViewPanel oppoHeroField;
    private HeroDeck oppoHeroDeck;
    private HeroPanel oppoHeroPanel;

    public ArrayList<JButton> getCurHandButtons() {
        return curHandButtons;
    }

    public ArrayList<JButton> getCurFieldButtons() {
        return curFieldButtons;
    }

    public ArrayList<JButton> getOppoFieldButtons() {
        return oppoFieldButtons;
    }

    public ArrayList<JButton> getOppoHandButtons() {
        return oppoHandButtons;
    }

    public ArrayList<MinionPanel> getCurHandPanels() {
        return curHandPanels;
    }

    public ArrayList<MinionPanel> getCurFieldPanels() {
        return curFieldPanels;
    }

    public ArrayList<MinionPanel> getOppoFieldPanels() {
        return oppoFieldPanels;
    }

    public ArrayList<MinionPanel> getOppoHandPanels() {
        return oppoHandPanels;
    }

    private ArrayList<JButton> curHandButtons;
    private ArrayList<JButton> curFieldButtons;
    private ArrayList<JButton> oppoFieldButtons;
    private ArrayList<JButton> oppoHandButtons;
    private ArrayList<MinionPanel> curHandPanels;
    private ArrayList<MinionPanel> curFieldPanels;
    private ArrayList<MinionPanel> oppoFieldPanels;
    private ArrayList<MinionPanel> oppoHandPanels;
    private ArrayList<Card> curHandCards;
    private ArrayList<Card> curFieldCards;
    private ArrayList<Card> oppoFieldCards;
    private ArrayList<Card> oppoHandCards;

    public ArrayList<Card> getCurHandCards() {
        return curHandCards;
    }

    public ArrayList<Card> getCurFieldCards() {
        return curFieldCards;
    }

    public ArrayList<Card> getOppoFieldCards() {
        return oppoFieldCards;
    }

    public ArrayList<Card> getOppoHandCards() {
        return oppoHandCards;
    }



    public JLabel getHerosMenueLabel() {
        return herosMenueLabel;
    }

    public GameView() {
        curHandCards = new ArrayList<>();
        curFieldCards = new ArrayList<>();
        oppoFieldCards = new ArrayList<>();
        oppoHandCards = new ArrayList<>();
        curHandButtons = new ArrayList<>();
        curFieldButtons = new ArrayList<>();
        oppoFieldButtons = new ArrayList<>();
        oppoHandButtons = new ArrayList<>();
        curHandPanels = new ArrayList<>();
        curFieldPanels = new ArrayList<>();
        oppoFieldPanels = new ArrayList<>();
        oppoHandPanels = new ArrayList<>();

        setSize(new Dimension(1440, 810));
        try {
            setContentPane(new ImagePanel(ImageIO.read(new File("images/backgrounds/main.jpg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("HearthStone");
        setResizable(false);
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setLayout(null);
        insets = getInsets();
//      Heros menue
        herosMenue = new JPanel();
        herosMenue.setLayout(new GridLayout(0, 1));
        herosMenue.add(herosMenueLabel);
        herosMenueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        herosMenueLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(herosMenue);
        herosMenue.setPreferredSize(new Dimension(400, getHeight() - 100));
        size = herosMenue.getPreferredSize();
        herosMenue.setBounds(520 + insets.left, 10 + insets.right, size.width, size.height);

        //        Exit Button
        exit = new JButton("Exit");

        add(exit);

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        size = exit.getPreferredSize();
        exit.setBounds(insets.left + 1350, insets.right + 710, size.width, size.height);

        pack();
        setVisible(true);
        revalidate();
        repaint();
    }


    public void createGamePlay(String n1, String n2) {

        remove(herosMenue);
        remove(exit);
        currPanel = new JPanel();
        currPanel.setPreferredSize(new Dimension(1400, 380));
        size = currPanel.getPreferredSize();
        currPanel.setBounds(insets.left + 10, insets.right + 10, size.width, size.height);
        currPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        oppoPanel = new JPanel();
        oppoPanel.setPreferredSize(new Dimension(1400, 380));
        size = oppoPanel.getPreferredSize();
        oppoPanel.setBounds(insets.left + 10, insets.right + 10 + currPanel.getHeight(), size.width, size.height);
        oppoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel temp = currPanel;
        currPanel = oppoPanel;
        oppoPanel = temp;
        currPanel.setLayout(null);
        oppoPanel.setLayout(null);
        add(currPanel);
        add(oppoPanel);


// Filling Current player side
        curHeroPanel = new HeroPanel(currPanel);
        curHeroDeck = new HeroDeck(currPanel);
        curHeroField = new FieldViewPanel(currPanel, currPanel.getInsets(), 230, 10, "Field", 7);
        curHeroHand = new FieldViewPanel(currPanel, curHeroField.getInsets(), 230, curHeroField.getHeight() + 20, "Hand", 10);
// Filling Opponent Side
        oppoHeroPanel = new HeroPanel(oppoPanel);
        positioning(oppoHeroPanel, oppoPanel.getInsets(), (oppoPanel.getWidth()) - 210, 20);
        oppoHeroDeck = new HeroDeck(oppoPanel);
        positioning(oppoHeroDeck, oppoPanel.getInsets(), 20, 20);
        oppoHeroHand = new FieldViewPanel(oppoPanel, oppoPanel.getInsets(), 230, 10, "Hand", 10);
        oppoHeroField = new FieldViewPanel(oppoPanel, curHeroHand.getInsets(), 230, curHeroHand.getHeight() + 20, "Field", 7);
//

        endTurn = new JButton("End Turn");
        endTurn.setPreferredSize(new Dimension(150, 20));
        currPanel.setLayout(new BorderLayout());
        oppoPanel.setLayout(new BorderLayout());
        positioning(endTurn, getInsets(), getWidth() - 220, (getHeight() / 2) - 30);
        add(endTurn);

//
        curHeroPanel.getHeroName().setText(n1);
        oppoHeroPanel.getHeroName().setText(n2);
        pack();
        revalidate();
        repaint();
    }

    public void intializeHeroPanel(Hero cur, GameController listener) {
        HeroPanel heroPanel = cur.getName().equals(getCurHeroPanel().getHeroName().getText()) ? getCurHeroPanel() : getOppoHeroPanel();
        FieldViewPanel heroHand = cur.getName().equals(getCurHeroPanel().getHeroName().getText()) ? getCurHeroHand() : getOppoHeroHand();
        HeroDeck heroDeck = cur.getName().equals(getCurHeroPanel().getHeroName().getText()) ? getCurHeroDeck() : getOppoHeroDeck();
        ArrayList<JButton> handButtons = cur.getName().equals(getCurHeroPanel().getHeroName().getText()) ? curHandButtons:oppoHandButtons;
        ArrayList<MinionPanel> handPanels = cur.getName().equals(getCurHeroPanel().getHeroName().getText()) ? curHandPanels : oppoHandPanels;
        ArrayList<Card> handCards = cur.getName().equals(getCurHeroPanel().getHeroName().getText()) ? curHandCards:oppoHandCards;

        heroPanel.getHeroInfo().setText("Name: " + cur.getName() + "\n" + "Current HP: " + cur.getCurrentHP() + "\n" + "Total mana crystals: " + cur.getTotalManaCrystals() + "\n" + "Current mana crystals: " + cur.getCurrentManaCrystals());
        heroDeck.getCurHeroDeckInfo().setText("Cards left in your deck:\n" + cur.getDeck().size());
        handButtons.removeAll(Collections.EMPTY_LIST);
        handPanels.removeAll(Collections.EMPTY_LIST);
        handCards.removeAll(Collections.EMPTY_LIST);

        heroHand.removeAll();
        pack();
        revalidate();
        repaint();

        for (int i = 0; i < cur.getHand().size(); i++) {
            Card m = cur.getHand().get(i);
            MinionPanel mm = new MinionPanel(heroHand);
            mm.getSelectButton().addActionListener(listener);

            handButtons.add(mm.getSelectButton());
            handPanels.add(mm);
            handCards.add(m);

            mm.getMinionName().setText(m.getName());
            if (m instanceof Minion) {
                String taunt = ((Minion) m).isTaunt() ? "Yes" : "No";
                String divineShield = ((Minion) m).isDivine() ? "Yes" : "No";
                String charge = ((Minion) m).isSleeping() ? "No" : "Yes";
                mm.getMinionInfo().setText("Name: " + m.getName() + "\n" + "Mana cost: " + m.getManaCost() + "\n" + "Rarity: " + m.getRarity() + "\n" + "Attack: " + ((Minion) m).getAttack() + "\n" + "Current HP: " + ((Minion) m).getCurrentHP() + "\n" + "Taunt: " + taunt + "\n" + "Divine Shield: " + divineShield + "\n" + "Charge: " + charge);
            } else
                mm.getMinionInfo().setText("Name: " + m.getName() + "\n" + "Mana cost: " + m.getManaCost() + "\n" + "Rarity: " + m.getRarity());
        }

        pack();
        revalidate();
        repaint();
    }

    public void intializeFirstTurn(Hero cur, Hero opponent, GameController listener) {
        intializeHeroPanel(cur, listener);
        intializeHeroPanel(opponent, listener);

    }


    public JPanel getCurrPanel() {
        return currPanel;
    }

    public JPanel getOppoPanel() {
        return oppoPanel;
    }

    public JButton getExit() {
        return exit;
    }

    public JButton getEndTurn() {
        return endTurn;
    }

    public HeroPanel getCurHeroPanel() {
        return curHeroPanel;
    }

    public HeroDeck getCurHeroDeck() {
        return curHeroDeck;
    }

    public FieldViewPanel getCurHeroField() {
        return curHeroField;
    }

    public FieldViewPanel getCurHeroHand() {
        return curHeroHand;
    }

    public FieldViewPanel getOppoHeroHand() {
        return oppoHeroHand;
    }

    public FieldViewPanel getOppoHeroField() {
        return oppoHeroField;
    }

    public HeroDeck getOppoHeroDeck() {
        return oppoHeroDeck;
    }

    public HeroPanel getOppoHeroPanel() {
        return oppoHeroPanel;
    }

    public static void positioning(Component c, Insets insets, int x, int y) {
        Dimension size = c.getPreferredSize();
        c.setBounds(insets.left + x, insets.right + y, size.width, size.height);
    }

    class ImagePanel extends JComponent {
        private Image image;

        public ImagePanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, 1440, 810, this);
        }
    }

}