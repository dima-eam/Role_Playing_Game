package org.eam.games.wanderer.engine;

import java.awt.Graphics;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.properties.GameProperties;

/**
 * Controls the transition of camera, centered on a specific "character", usually a hero by calling
 * {@link Camera#translate(Graphics)} method.
 */
@AllArgsConstructor
public class Camera {

    private final Position centerOn;
    private final GameProperties properties;

    public Graphics translate(Graphics graphics) {
        int camXPos = clamp(centerOn.xTile() * properties.getTileSize(), properties.getBoundLeft(),
            properties.getBoundBottom());
        int camYPos = clamp(centerOn.yTile() * properties.getTileSize(), properties.getBoundTop(),
            properties.getBoundBottom());

        int offSetX = -camXPos + properties.getScreenSize().width / 2;
        int offSetY = -camYPos + properties.getScreenSize().height / 2;
        graphics.translate(offSetX, offSetY);

        return graphics;
    }

    /**
     * Returns a number between min and max to be between them: while x < high and x > low return x.
     */
    private static int clamp(int x, int low, int high) {
        return Math.max(low, Math.min(high, x));
    }

}
