package org.eam.games.wanderer.engine;

import java.util.Optional;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.actor.Direction;
import org.eam.games.wanderer.actor.WithStats;
import org.eam.games.wanderer.world.Cell;
import org.eam.games.wanderer.world.Tile;
import org.eam.games.wanderer.world.World;

/**
 * Encapsulates the current cell of player entity, and controls transition based on direction. Main purpose is movement
 * condition checks and direction for displaying player's entity.
 */
@Log4j2
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerMovement implements WithStats {

    @Getter
    private final Cell current;
    private final Predicate<Tile> canPass = tile -> !tile.isSolid();
    private Direction direction;

    /**
     * Initial position in upper-left corner, face down.
     */
    public static PlayerMovement start() {
        return new PlayerMovement(new Cell(0, 0), Direction.DOWN);
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
     * Current direction of an entity
     */
    public Direction direction() {
        return direction;
    }

    @Override
    public String stats() {
        return "POS: " + current.getXTile() + ", " + current.getYTile() + " | DIR: " + direction;
    }

    /**
     * Changes the direction (always), and location if possible for that direction.
     */
    void move(Direction direction,
        World world) { // todo think about injecting world, making tileForCell interface method
        this.direction = direction;

        Cell newCell = switch (direction) {
            case DOWN -> current.moveDown();
            case UP -> current.moveUp();
            case LEFT -> current.moveLeft();
            case RIGHT -> current.moveRight();
        };
        Optional<Tile> tile = world.tileForCell(newCell);
        tile
//            .filter(canPass)
            .ifPresent(t -> current.move(newCell));
        log.trace("Moving: direction={}, cell={}, nextTile={}", () -> direction, () -> current, () -> tile);
    }

}
