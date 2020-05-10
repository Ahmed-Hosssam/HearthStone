package view;

import javax.swing.*;
import java.awt.*;

public class FieldViewPanel extends JPanel {
    public FieldViewPanel (JPanel panel,Insets insets,int x, int y,String label,int size){
        add(new JLabel(label));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(950,(panel.getHeight()/2)-20));
        GameView.positioning(this,insets,x,y);
        panel.add(this);
        setLayout(new GridLayout(1,size));
    }

    public static void main(String[] args) {

    }

}
