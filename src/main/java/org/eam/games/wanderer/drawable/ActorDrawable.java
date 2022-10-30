package org.eam.games.wanderer.drawable;

import java.awt.Graphics;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.actor.Actor;
import org.eam.games.wanderer.engine.Position;

/**
 * Controls any actor (moving entity) drawing logic. Each actor has its current position and fixed step, controlling how
 * many tiles can be passed in one turn. Requires {@link Actor} reference to determine an image for a direction.
 */
@AllArgsConstructor
public class ActorDrawable implements Drawable {

    private final Actor actor;
    private final int step;
    private final Position position;

    /**
     * @inheritDoc
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(actor.imageForDirection(position.direction()),
            position.xTile() * step, position.yTile() * step,
            null);
    }

}
