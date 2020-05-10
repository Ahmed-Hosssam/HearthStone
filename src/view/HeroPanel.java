package view;



import javax.swing.*;
import java.awt.*;

public class HeroPanel extends JPanel {
    public HeroPanel(JPanel panel){

        setPreferredSize(new Dimension(200,280));
        GameView.positioning(this, panel.getInsets(),(panel.getWidth()) - 210,panel.getHeight()-300);
        JButton selectCurHeroButton = new JButton("Select");
        add(selectCurHeroButton,BorderLayout.NORTH);
        JTextArea curHeroInfo = new JTextArea();
        curHeroInfo.setText("name:\n current HP:");
        curHeroInfo.setEditable(false);
        curHeroInfo.setPreferredSize(new Dimension(180,200));
        add(curHeroInfo,BorderLayout.SOUTH);
       setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(this);
    }

}
