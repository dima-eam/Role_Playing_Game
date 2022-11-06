package org.eam.games.wanderer.drawable;

import lombok.AllArgsConstructor;
import org.eam.games.wanderer.actor.Actor;
import org.eam.games.wanderer.engine.PlayerMovement;

/**
 * Controls player drawing logic. Player's character has its current position and fixed step, controlling how many tiles
 * can be passed in one turn, considering the game world scrolling to not move more tiles than expected. Requires
 * player's {@link Actor} reference to determine an image for a direction.
 */
@AllArgsConstructor
public class PlayerDrawable implements Drawable {

    private final Actor actor;
    private final int step;
    private final PlayerMovement movement;

    /**
     * @inheritDoc
     */
    @Override
    public void draw(GraphicsContext context) {
        context.process(g -> g.drawImage(actor.imageForDirection(movement.direction()),
            (movement.xTile() - context.getXOffset()) * step, (movement.yTile() - context.getYOffset()) * step,
            null));
    }

}
