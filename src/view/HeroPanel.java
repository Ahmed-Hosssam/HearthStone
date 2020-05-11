package view;



import javax.swing.*;
import java.awt.*;

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

    public HeroPanel(JPanel panel){

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
        add(useHeroPower,BorderLayout.SOUTH);
        attackHero = new JButton("Attack");
        add(attackHero,BorderLayout.SOUTH);
        panel.add(this);

    }

}
