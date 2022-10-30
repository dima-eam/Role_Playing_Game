package org.eam.games.wanderer.engine;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.actor.Direction;
import org.eam.games.wanderer.world.Cell;
import org.eam.games.wanderer.world.World;

/**
 * Encapsulates the current cell of some actor/entity, and controls transition based on direction. Since this class
 * instances have no knowledge about what exactly is being tracked, the control is performed externally.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Position {

    private final Cell current;
    private Direction direction;

    /**
     * Initial position in upper-left corner, face down.
     */
    public static Position start() {
        return new Position(new Cell(0, 0), Direction.DOWN);
    }

    /**
     * Number of tiles from upper-left corner, X axis.
     */
    public int xTile() {
        return current.getXTile();
    }

    /**
     * Number of tiles from upper-left corner, Y axis.
     */
    public int yTile() {
        return current.getYTile();
    }

    /**
     * Changes the direction (always), and location if possible for that direction.
     */
    public void move(Direction direction, World world) {
        this.direction = direction;

        Cell newCell = switch (direction) {
            case DOWN -> current.moveDown();
            case UP -> current.moveUp();
            case LEFT -> current.moveLeft();
            case RIGHT -> current.moveRight();
        };
        world.possbileMove(newCell)
            .ifPresent(current::move);
    }

    /**
     * Current direction of an entity
     */
    public Direction direction() {
        return direction;
    }

}
