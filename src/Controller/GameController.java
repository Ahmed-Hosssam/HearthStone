package Controller;

import engine.GameListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements GameListener, ActionListener {




    @Override
    public void onGameOver() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new GameController();
    }
}
