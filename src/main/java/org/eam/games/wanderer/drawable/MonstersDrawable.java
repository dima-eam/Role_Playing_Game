package org.eam.games.wanderer.drawable;

import java.awt.Graphics;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.actor.Direction;
import org.eam.games.wanderer.engine.Monsters;

@AllArgsConstructor
public class MonstersDrawable implements Drawable {

    private final Monsters monsters;

    @Override
    public void draw(GraphicsContext context) {
        context.process(graphics -> drawMonsters(graphics, context.getXOffset(), context.getYOffset()));
    }

    private void drawMonsters(Graphics graphics, int xOffset, int yOffset) {
        monsters.forEach((cell, actor) -> graphics.drawImage(actor.imageForDirection(Direction.UP),
            (cell.getXTile() - xOffset) * 72, (cell.getYTile() - yOffset) * 72,
            null));
    }

}
