package org.eam.games.wanderer.engine;


import java.awt.Dimension;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.drawable.Drawable;
import org.eam.games.wanderer.ui.Hud;

/**
 * Main game panel. Stores every game sub-system: {@link Drawable} instances for game actors, such as hero and monsters,
 * pre-generated game world ("Maze"), and keyboard events processor.
 */
@Log4j2
@Deprecated(forRemoval = true)
public class GameEngine {

    /**
     * Screen repaint interval, consumed by the timer. Timer will fire its events calling actionPerformed() method.
     */
    private static final int INTERVAL = 50;

    private final Dimension screenSize;
    private final GameController controller;
    private final Hud hud;
//    private final DrawCharacter drawCharacter;

    public GameEngine(Dimension screenSize) {
        this.screenSize = screenSize;

//        addKeyListener(new TAdapter());
//        setFocusable(true);
//        setBackground(Color.WHITE);

        controller = new GameController();
        hud = new Hud();
//        drawMaze = new DrawMaze( controller.getMaze());
//        drawCharacter = new DrawCharacter();
//
//        maze = controller.getMaze();
        hud.setGameController(controller);

    }


}
