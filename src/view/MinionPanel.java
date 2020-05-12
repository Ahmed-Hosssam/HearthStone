package view;



import Controller.GameController;
import model.cards.minions.Minion;

import javax.swing.*;
import java.awt.*;

import static Controller.GameController.buttons;

public class MinionPanel extends JPanel {
    private JTextArea MinionInfo;
    private JLabel MinionName;
    private JButton selectButton;
    private JButton attackButton;


    public JTextArea getMinionInfo() {
        return MinionInfo;
    }

    public JLabel getMinionName() {
        return MinionName;
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public MinionPanel(JPanel panel,String s,GameController listener,boolean f){

        setLayout(new BorderLayout());
        MinionName = new JLabel();
        selectButton = new JButton("add to field");
        add(MinionName,BorderLayout.NORTH);
        MinionInfo = new JTextArea();
        MinionInfo.setText("");
        MinionInfo.setEditable(false);
        MinionInfo.setPreferredSize(new Dimension(180,200));
        add(MinionInfo,BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.black));
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
        add(selectButton,BorderLayout.SOUTH);

        panel.add(this);

    }


}
