package org.eam.games.wanderer.engine.tile;

import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.engine.tile.OrientedTileset.Orientation;

/**
 * Randomized access to various tiles by direction.
 */
@AllArgsConstructor
public class OrientedTiles {

    private static final Random RANDOM = new Random();

    private final Tileset tileset;

    public Tile nextTile(Orientation type) {
        List<Tile> tiles = tileset.tileset().get(type);
        if (tiles.size() == 1) {
            return tiles.get(0);
        }

        return tiles.get(RANDOM.nextInt(2));
    }

}
