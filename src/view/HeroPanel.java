package view;



import Controller.GameController;

import javax.swing.*;
import java.awt.*;

import static Controller.GameController.buttons;
import static Controller.GameController.cards;

public class HeroPanel extends JPanel {
    private JTextArea HeroInfo;
    private JLabel HeroName;
    private JButton useHeroPower;
    private JButton attackHero;
    public JTextArea getHeroInfo() {
        return HeroInfo;
    }

    public JLabel getHeroName() {
        return HeroName;
    }

    public JButton getUseHeroPower() {
        return useHeroPower;
    }

    public HeroPanel(JPanel panel, GameController listener,boolean f){

        setPreferredSize(new Dimension(200,300));
        GameView.positioning(this, panel.getInsets(),(panel.getWidth()) - 210,panel.getHeight()-320);
        HeroName = new JLabel();
        useHeroPower = new JButton("Use Hero Power");
        add(HeroName,BorderLayout.NORTH);
        HeroInfo = new JTextArea();
        HeroInfo.setText("name:\n current HP:");
        HeroInfo.setEditable(false);
        HeroInfo.setPreferredSize(new Dimension(180,200));
        add(HeroInfo,BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.black));
        if (f) {
            add(useHeroPower, BorderLayout.SOUTH);
            buttons.add(useHeroPower);
            cards.add("useHeroPower");
            JButton select = new JButton("select");
            add(select,BorderLayout.SOUTH);
            buttons.add(select);
            cards.add("currentHero");
            select.addActionListener(listener);
        }
        attackHero = new JButton("Attack");
        if (!f) {
            add(attackHero, BorderLayout.SOUTH);
            buttons.add(attackHero);
            cards.add("opponent");
            JButton select = new JButton("select");
            add(select,BorderLayout.SOUTH);
            buttons.add(select);
            cards.add("opponentHero");
            select.addActionListener(listener);
        }
        attackHero.addActionListener(listener);
        useHeroPower.addActionListener(listener);
        panel.add(this);

    }

}
