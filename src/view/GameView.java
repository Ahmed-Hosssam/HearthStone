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
    private JLabel herosMenueLabel = new JLabel("First Player:");

    public JLabel getHerosMenueLabel() {
        return herosMenueLabel;
    }

    public GameView(){

        setSize(new Dimension(1440,810));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("HearthStone");
        setResizable(false);
        setMinimumSize(new Dimension(getWidth(),getHeight()));
        setMinimumSize(new Dimension(getWidth(),getHeight()));
        setVisible(true);
        try {
            setContentPane(new ImagePanel(ImageIO.read(new File("images/backgrounds/main.jpg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(null);
        Insets insets = getInsets();




//      Heros menue
        herosMenue = new JPanel();
        herosMenue.setLayout(new GridLayout(0,1));
        herosMenue.add(herosMenueLabel);
        add(herosMenue);
        herosMenue.setPreferredSize(new Dimension(400,getHeight()-100));
        Dimension size = herosMenue.getPreferredSize();
        herosMenue.setBounds(520+insets.left,10+insets.right,size.width,size.height);

        //        Exit Button
        JButton exit = new JButton("Exit");

        add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        size = exit.getPreferredSize();
        exit.setBounds(insets.left + 1350,insets.right + 710,size.width,size.height);
        //playSound("sounds/Background Music/Mulligan.ogg");
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
