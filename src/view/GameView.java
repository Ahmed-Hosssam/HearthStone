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
    private Font font;
    public GameController getListener() {
        return listener;
    }

    private FieldViewPanel curHeroField;
    private FieldViewPanel curHeroHand;
    private FieldViewPanel oppoHeroHand;
    private FieldViewPanel oppoHeroField;
    private HeroDeck oppoHeroDeck;
    private HeroPanel oppoHeroPanel;
    private GameController listener;

    public void setListener(GameController listener) {
        this.listener = listener;
    }







    public JLabel getHerosMenueLabel() {
        return herosMenueLabel;
    }



    public GameView() {

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
        endTurn = new JButton("End Turn");
        endTurn.setPreferredSize(new Dimension(150, 20));

        positioning(endTurn, getInsets(), getWidth() - 220, (getHeight() / 2) - 30);
        add(endTurn);
        currPanel = new JPanel();
        currPanel.setPreferredSize(new Dimension(1400, 380));
        size = currPanel.getPreferredSize();
        currPanel.setBounds(insets.left + 10, insets.right + 10, size.width, size.height);
        //currPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        oppoPanel = new JPanel();
        oppoPanel.setPreferredSize(new Dimension(1400, 380));
        size = oppoPanel.getPreferredSize();
        oppoPanel.setBounds(insets.left + 10, insets.right + 10 + currPanel.getHeight(), size.width, size.height);
        //oppoPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        JPanel temp = currPanel;
        currPanel = oppoPanel;
        oppoPanel = temp;
        currPanel.setLayout(null);
        oppoPanel.setLayout(null);
        add(currPanel);
        add(oppoPanel);


// Filling Current player side
        curHeroPanel = new HeroPanel(currPanel,listener,true);
        curHeroDeck = new HeroDeck(currPanel);
        curHeroField = new FieldViewPanel(currPanel, currPanel.getInsets(), 230, 10, "Field", 7);
        curHeroHand = new FieldViewPanel(currPanel, curHeroField.getInsets(), 230, curHeroField.getHeight() + 20, "Hand", 10);
// Filling Opponent Side
        oppoHeroPanel = new HeroPanel(oppoPanel,listener,false);
        oppoHeroDeck = new HeroDeck(oppoPanel);
        oppoHeroHand = new FieldViewPanel(oppoPanel, oppoPanel.getInsets(), 230, 10, "Hand", 10);
        oppoHeroField = new FieldViewPanel(oppoPanel, curHeroHand.getInsets(), 230, curHeroHand.getHeight() + 20, "Field", 7);
        positioning(oppoHeroPanel, oppoPanel.getInsets(), (oppoPanel.getWidth()) - 210, 20);
        positioning(oppoHeroDeck, oppoPanel.getInsets(), 20, 20);

        currPanel.setLayout(new BorderLayout());
        oppoPanel.setLayout(new BorderLayout());


//
        curHeroPanel.getHeroName().setText(n1);
        oppoHeroPanel.getHeroName().setText(n2);

        currPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        oppoPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        curHeroPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        curHeroDeck .setBackground(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        curHeroField.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        oppoHeroPanel .setBackground(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        oppoHeroDeck .setBackground(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        oppoHeroHand .setBackground(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        oppoHeroField.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.1f));

        GameController.playSound("sounds/Background Music/The_Forge.wav");
        pack();
        revalidate();
        repaint();
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

    public class ImagePanel extends JComponent {
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