package view;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
    public JLabel getHerosMenueLabel() {
        return herosMenueLabel;
    }

    public GameView(){

        setSize(new Dimension(1440,810));
        try {
            setContentPane(new ImagePanel(ImageIO.read(new File("images/backgrounds/main.jpg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("HearthStone");
        setResizable(false);
        setMinimumSize(new Dimension(getWidth(),getHeight()));
        setMinimumSize(new Dimension(getWidth(),getHeight()));



        setLayout(null);
        insets = getInsets();




//      Heros menue
        herosMenue = new JPanel();
        herosMenue.setLayout(new GridLayout(0,1));
        herosMenue.add(herosMenueLabel);
        herosMenueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        herosMenueLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(herosMenue);
        herosMenue.setPreferredSize(new Dimension(400,getHeight()-100));
        size = herosMenue.getPreferredSize();
        herosMenue.setBounds(520 + insets.left,10 + insets.right,size.width,size.height);

        //        Exit Button
        exit = new JButton("Exit");

        add(exit);

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        size = exit.getPreferredSize();
        exit.setBounds(insets.left + 1350,insets.right + 710,size.width,size.height);

        pack();
        setVisible(true);
        revalidate();
        repaint();
    }


    public void createGamePlay () {

        remove(herosMenue);
        remove(exit);
        currPanel = new JPanel();
        currPanel.setPreferredSize(new Dimension(1400,380));
        size = currPanel.getPreferredSize();
        currPanel.setBounds(insets.left + 10,insets.right+10,size.width,size.height);
        currPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        oppoPanel = new JPanel();
        oppoPanel.setPreferredSize(new Dimension(1400,380));
        size = oppoPanel.getPreferredSize();
        oppoPanel.setBounds(insets.left + 10,insets.right+10+ currPanel.getHeight(),size.width,size.height);
        oppoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel temp = currPanel;
        currPanel = oppoPanel;
        oppoPanel = temp;
        currPanel.setLayout(null);
        oppoPanel.setLayout(null);
        add(currPanel);
        add(oppoPanel);

//      styling current hero panel -> the one in the bottom
        JPanel curHeroPanel = new JPanel();
        curHeroPanel.setPreferredSize(new Dimension(200,300));
        positioning(curHeroPanel, currPanel.getInsets(),(currPanel.getWidth()/2) - 100,currPanel.getHeight()-300);

        JButton selectCurHero = new JButton("Select");
        curHeroPanel.add(selectCurHero);
        JTextArea curHeroInfo = new JTextArea();
        curHeroInfo.setText("gskjgkshddghdg \n ;lfjklgjlslsgl");
        curHeroInfo.setEditable(false);
        curHeroPanel.add(curHeroInfo);
        curHeroPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        currPanel.add(curHeroPanel);



        pack();
        revalidate();
        repaint();
    }

    public void positioning (Component c ,Insets insets, int x,int y) {
        Dimension size = c.getPreferredSize();
        c.setBounds(insets.left + x,insets.right+y,size.width,size.height);
    }

    class ImagePanel extends JComponent {
        private Image image;
        public ImagePanel(Image image) {
            this.image = image;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, 1440,810,this);
        }
    }




}
