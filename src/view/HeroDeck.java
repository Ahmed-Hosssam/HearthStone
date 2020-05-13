package view;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class HeroDeck extends JPanel {
    public JTextPane getCurHeroDeckInfo() {
        return curHeroDeckInfo;
    }

    private JTextPane curHeroDeckInfo;

    public HeroDeck(JPanel panel){
        this.setPreferredSize(new Dimension(200,280));
        GameView.positioning(this,panel.getInsets(),20,panel.getHeight()-300);
        curHeroDeckInfo = new JTextPane();
        curHeroDeckInfo.setText("name:\n current HP:");
        curHeroDeckInfo.setEditable(false);
        curHeroDeckInfo.setPreferredSize(new Dimension(180,240));
       // setBorder(BorderFactory.createLineBorder(Color.white));
        add(curHeroDeckInfo);
        StyledDocument doc = curHeroDeckInfo.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        Font font = new Font("SERIF", Font.PLAIN, 16);
        curHeroDeckInfo.setFont(font);
        curHeroDeckInfo.setBackground(Color.lightGray);
        curHeroDeckInfo.setForeground(Color.darkGray);
        panel.add(this);


    }

}
