package org.eam.games.wanderer.world;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Simple representation of a tile coordinates in the world, measured in tiles (not pixels) from top-left corner.
 */
@AllArgsConstructor
@Data
public class Cell { // todo remove getters

    private int xTile;
    private int yTile;

    public Cell moveDown() {
        return new Cell(xTile, yTile + 1);
    }

    public Cell moveUp() {
        return new Cell(xTile, yTile - 1);
    }

    public Cell moveLeft() {
        return new Cell(xTile - 1, yTile);
    }

    public Cell moveRight() {
        return new Cell(xTile + 1, yTile);
    }

    public void move(Cell cell) {
        xTile = cell.getXTile();
        yTile = cell.getYTile();
    }

}
