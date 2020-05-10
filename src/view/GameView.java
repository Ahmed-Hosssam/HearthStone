package view;



import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
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
    private JLabel herosMenueLabel = new JLabel("First Player Hero");
    private Insets insets;
    private Dimension size;
    private JPanel curr;
    private JPanel oppo;
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
        herosMenue.setBounds(520+insets.left,10+insets.right,size.width,size.height);

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
        curr = new JPanel();
        curr.setPreferredSize(new Dimension(1400,380));
        size = curr.getPreferredSize();
        curr.setBounds(insets.left + 10,insets.right+10,size.width,size.height);
        curr.setBorder(BorderFactory.createLineBorder(Color.black));
        oppo = new JPanel();
        oppo.setPreferredSize(new Dimension(1400,380));
        size = oppo.getPreferredSize();
        oppo.setBounds(insets.left + 10,insets.right+10+curr.getHeight(),size.width,size.height);
        oppo.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel temp = curr;
        curr = oppo;
        oppo = temp;
        add(curr);
        add(oppo);



        pack();
        revalidate();
        repaint();
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

    public static void main(String[] args) {

    }



}
