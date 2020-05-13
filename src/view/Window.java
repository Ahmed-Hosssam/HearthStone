package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {

    public Window (String s) {

        setLocationRelativeTo(null);
        setTitle("Alert");
        setResizable(false);
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        add(new JTextArea(s),BorderLayout.CENTER);

        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(ok,BorderLayout.SOUTH);
        setVisible(true);
        pack();
        revalidate();
        repaint();
    }
}
