package org.eam.games.wanderer.engine;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.eam.games.wanderer.world.Cell;

/**
 * Mutable representation of a tile coordinates in the world, measured in tiles (not pixels) from top-left corner.
 * Allows to simulate movement via changing object state, which is tracked by movement controller.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
class Position {

    private int xTile;
    private int yTile;

    static Position from(Cell cell) {
        return new Position(cell.getXTile(), cell.getYTile());
    }

    Position down() {
        return new Position(xTile, yTile + 1);
    }

    Position up() {
        return new Position(xTile, yTile - 1);
    }

    Position left() {
        return new Position(xTile - 1, yTile);
    }

    Position right() {
        return new Position(xTile + 1, yTile);
    }

    void moveTo(Position position) {
        xTile = position.xTile;
        yTile = position.yTile;
    }

    int xOffset(int xOffset) {
        return xTile - xOffset;
    }

    int yOffset(int yOffset) {
        return yTile - yOffset;
    }

}
