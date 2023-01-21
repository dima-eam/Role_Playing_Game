package org.eam.games.wanderer.drawable;

import lombok.AllArgsConstructor;
import org.eam.games.wanderer.engine.Monsters;
import org.eam.games.wanderer.properties.GameProperties;

@AllArgsConstructor
public class MonstersDrawable implements Drawable {

    private final Monsters monsters;
    private final GameProperties properties;

    @Override
    public void draw(GraphicsContext context) {
        monsters.forEach(m -> context.process(m.image(),
            m.xOffset(context.getYOffset(), properties.getTileSize()),
            m.yOffset(context.getYOffset(), properties.getTileSize()),
            properties.getTileSize()));
    }

}
