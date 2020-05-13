package view;



import Controller.GameController;
import model.cards.minions.Minion;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

import static Controller.GameController.buttons;

public class MinionPanel extends JPanel {
    private JTextPane MinionInfo;
    private JLabel MinionName;
    private JButton selectButton;
    private JButton attackButton;
    private String cardType;

    public JTextPane getMinionInfo() {
        return MinionInfo;
    }

    public JLabel getMinionName() {
        return MinionName;
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public JButton getAttackButton() {
        return attackButton;
    }

    public String getCardType() {
        return cardType;
    }

    public MinionPanel(JPanel panel, String s, String cardType, GameController listener, boolean f){
        this.cardType = cardType;
        setLayout(new BorderLayout());
        MinionName = new JLabel();
        selectButton = new JButton("add to field");

        add(MinionName,BorderLayout.NORTH);
        MinionInfo = new JTextPane();
        MinionInfo.setText("");
        MinionInfo.setEditable(false);
        MinionInfo.setPreferredSize(new Dimension(180,200));
        add(MinionInfo,BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.white));
        selectButton.addActionListener(listener);
        attackButton = new JButton("Attack");
        if (s.equals("field")) {
            selectButton.setText("select");
            if (!f) {
                add(attackButton, BorderLayout.NORTH);
                buttons.add(attackButton);

            }
            attackButton.addActionListener(listener);
        }
        if (cardType.equals("spell")) {
            selectButton.setText("cast");
        }
        StyledDocument doc = MinionInfo.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        add(selectButton,BorderLayout.SOUTH);

        Font font = new Font("SERIF", Font.BOLD, 10);
        MinionInfo.setFont(font);
        MinionInfo.setBackground(Color.lightGray);
        MinionInfo.setForeground(Color.darkGray);
        panel.add(this);

    }


}
