package view;

import javax.swing.*;
import java.awt.*;

public class HeroDeck extends JPanel {
    public JTextArea getCurHeroDeckInfo() {
        return curHeroDeckInfo;
    }

    private JTextArea curHeroDeckInfo;

    public HeroDeck(JPanel panel){
        this.setPreferredSize(new Dimension(200,280));
        GameView.positioning(this,panel.getInsets(),20,panel.getHeight()-300);
        curHeroDeckInfo = new JTextArea();
        curHeroDeckInfo.setText("name:\n current HP:");
        curHeroDeckInfo.setEditable(false);
        curHeroDeckInfo.setPreferredSize(new Dimension(180,240));
        setBorder(BorderFactory.createLineBorder(Color.black));
        add(curHeroDeckInfo);
        panel.add(this);

    }

}
