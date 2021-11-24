package org.eam.games.wanderer.drawable;

import java.awt.Graphics;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.properties.GameProperties;
import org.eam.games.wanderer.world.World;

/**
 * Connects game world instance to rendering context.
 */
@AllArgsConstructor
public class WorldDrawable implements Drawable {

    private final GameProperties properties;
    private final World world;

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < properties.getWidthInTiles(); i++) {
            for (int j = 0; j < properties.getHeightInTiles(); j++) {
                g.drawImage(world.getTile(i, j).get().getTileImage(),
                    i * properties.getTileSize(),
                    j * properties.getTileSize(),
                    null);
            }
        }
    }

}
