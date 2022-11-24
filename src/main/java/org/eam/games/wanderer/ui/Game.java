package org.eam.games.wanderer.ui;

import java.awt.event.KeyAdapter;
import java.util.Arrays;
import javax.swing.JFrame;
import org.eam.games.wanderer.engine.GameTimer;
import org.eam.games.wanderer.properties.GameProperties;

/**
 * Game instance, derived from {@link JFrame}, adds game {@link Display} to the container to be displayed in borderless
 * full-screen mode, and enables other systems, such as input listeners.
 */
public class Game extends JFrame {

    public static void run(GameProperties properties, Display display, KeyAdapter... listeners) {
        Game game = new Game();
        game.add(display);
        Arrays.stream(listeners).forEach(game::addKeyListener);

        game.setSize(properties.getScreenSize());
        game.setUndecorated(true);
        game.setVisible(true);

        GameTimer.start(display);
    }

}
