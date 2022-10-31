package org.eam.games.wanderer.engine;

import java.awt.Graphics;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.properties.GameProperties;

/**
 * Controls the transition of camera, centered on a specific "character", usually a hero, by calling
 * {@link Camera#translate(Graphics)} method.
 */
@Log4j2
@AllArgsConstructor
public class Camera {

    private final Movement centerOn;
    private final GameProperties properties;

    /**
     * Modifies graphics context coordinates every time the tracked entity reaches the middle of the screen, to make the
     * world scrollable.
     */
    public Graphics translate(Graphics graphics) {
        int camXPos = clamp(centerOn.xTile() * properties.getTileSize(), properties.getBoundLeft(),
            properties.getBoundBottom());
        int camYPos = clamp(centerOn.yTile() * properties.getTileSize(), properties.getBoundTop(),
            properties.getBoundBottom());

        int offSetX = -camXPos + properties.getScreenSize().width / 2;
        int offSetY = -camYPos + properties.getScreenSize().height / 2;
        log.info("Camera offsets: camXPos={}, camYPos={}, x={}, y={}",
            ()->camXPos, ()->camYPos, () -> offSetX, () -> offSetY);

        graphics.translate(offSetX, offSetY);

        return graphics;
    }

    /**
     * Returns a number between min and max to be between them: while x < high and x > low return x, allowing the game
     * world to be scrollable when tracked entity reaches the middle of the screen.
     */
    private static int clamp(int x, int low, int high) {
        return Math.max(low, Math.min(high, x));
    }

}
