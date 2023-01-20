package org.eam.games.wanderer.world;

import java.util.Optional;
import org.eam.games.wanderer.world.tile.Tile;

/**
 * Defines a set of methods for any game world implementation, focused on work with {@link Tile} and {@link Cell}
 * instances for interaction, placement etc.
 */
public interface World {

    /**
     * Returns a walkable cell (the one where a monster/player can stay/walk), primarily for placing entities.
     */
    Cell walkableCell();

    /**
     * Optional tile for world generation, building a path for a monster, or for other checks. Tries to find a tile for
     * given cell. Might be empty if the world is not rectangular, or given coordinates have no tile.
     */
    Optional<Tile> tileForCell(Cell newCell);

    default Optional<Tile> tileFor(int xTile, int yTile) {
        return tileForCell(new Cell(xTile, yTile));
    }

    int widthInTiles();

    int heightInTiles();

    default Cell start() {
        return new Cell(0, 0);
    }

}
