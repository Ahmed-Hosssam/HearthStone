package view;



import Controller.GameController;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

import static Controller.GameController.buttons;
import static Controller.GameController.cards;

public class HeroPanel extends JPanel {
    private JTextPane HeroInfo;

    public void setHeroName(JLabel heroName) {
        HeroName = heroName;
    }

    private JLabel HeroName;
    private JButton useHeroPower;
    private JButton attackHero;
    public JTextPane getHeroInfo() {
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
        HeroInfo = new JTextPane();
        HeroInfo.setText("name:\n current HP:");
        HeroInfo.setEditable(false);
        HeroInfo.setPreferredSize(new Dimension(180,200));
        add(HeroInfo,BorderLayout.CENTER);
        //setBorder(BorderFactory.createLineBorder(Color.white));
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
        StyledDocument doc = HeroInfo.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        attackHero.addActionListener(listener);
        useHeroPower.addActionListener(listener);
        Font font = new Font("SERIF", Font.PLAIN, 16);
        HeroInfo.setFont(font);
        HeroInfo.setBackground(Color.lightGray);
        HeroInfo.setForeground(Color.darkGray);
        HeroName.setForeground(Color.white);
        panel.add(this);

    }

}
