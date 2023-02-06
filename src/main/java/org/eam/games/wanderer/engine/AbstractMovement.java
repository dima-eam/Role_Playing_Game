package org.eam.games.wanderer.engine;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.eam.games.wanderer.actor.Direction;

/**
 * Base class for moving entities. General purpose is drawing actors based on position and direction.
 */
@AllArgsConstructor(access = AccessLevel.PROTECTED)
class AbstractMovement {

    private final int tileSize;
    @Getter
    protected final Position current;
    protected Direction direction;

    public int xOffset(int xOffset) {
        return current.xOffset(xOffset) * tileSize;
    }

    public int yOffset(int yOffset) {
        return current.yOffset(yOffset) * tileSize;
    }

    /**
     * Current direction of an entity. Allows to determine what sprite to draw, or some interactions based on
     * direction.
     */
    public Direction direction() {
        return direction;
    }

}
