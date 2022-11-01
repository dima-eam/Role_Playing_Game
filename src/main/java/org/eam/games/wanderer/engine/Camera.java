package org.eam.games.wanderer.engine;

import java.awt.Graphics;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.drawable.GraphicsContext;
import org.eam.games.wanderer.properties.GameProperties;

/**
 * Controls the transition of camera, centered on a specific "character", usually a hero, by evaluating tiles offsets
 * for scrolling.
 */
@Log4j2
@AllArgsConstructor
public class Camera {

    private final Movement centerOn;
    private final GameProperties properties;

    /**
     * Evaluates tiles offsets for scrolling.
     */
    public GraphicsContext moved(Graphics graphics) {
        int xOffset = properties.xOffset(centerOn.xTile());
        int yOffset = properties.yOffset(centerOn.yTile());

        log.trace("Camera offsets: xOffset={}, yOffset={}", () -> xOffset, () -> yOffset);

        return GraphicsContext.from(graphics, xOffset, yOffset);
    }

}
