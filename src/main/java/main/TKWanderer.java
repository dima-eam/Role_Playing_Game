package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import main.models.Tile;
import main.views.Board;

public class TKWanderer extends JFrame {

    public static final int SCREEN_WIDTH = 16 * Tile.TILE_SIZE;
    public static final int SCREEN_HEIGHT = 9 * Tile.TILE_SIZE;

    public static void main(String[] args) {
        EventQueue.invokeLater(TKWanderer::init);
    }

    private static void init() {
        TKWanderer wanderer = new TKWanderer();
        wanderer.add(new Board());
        wanderer.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        wanderer.setResizable(false);
        wanderer.setTitle("TKWanderer");
        wanderer.setLocationRelativeTo(null);
        wanderer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        wanderer.setVisible(true);
    }

}
