package org.eam.games.wanderer.drawable;

import java.awt.Graphics;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.actor.Direction;
import org.eam.games.wanderer.engine.Monsters;
import org.eam.games.wanderer.properties.GameProperties;

@AllArgsConstructor
public class MonstersDrawable implements Drawable {

    private final Monsters monsters;
    private final GameProperties properties;

    @Override
    public void draw(GraphicsContext context) {
        context.process(graphics -> drawMonsters(graphics, context.getXOffset(), context.getYOffset()));
    }

    private void drawMonsters(Graphics graphics, int xOffset, int yOffset) {
        monsters.forEach(m -> graphics.drawImage(m.getMonster().imageForDirection(Direction.UP),
            m.xOffset(xOffset, properties.getTileSize()), m.yOffset(yOffset, properties.getTileSize()),
            null));
    }

}
