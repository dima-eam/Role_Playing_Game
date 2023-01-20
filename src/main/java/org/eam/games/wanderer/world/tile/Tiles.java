package org.eam.games.wanderer.world.tile;

import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.world.tile.OrientedTileset.TileType;

/**
 * Randomized access to various tiles by direction.
 */
@AllArgsConstructor
public class Tiles {

    private static final Random RANDOM = new Random();

    private final OrientedTileset tileset;

    public Tile nextTile(TileType type) {
        List<Tile> tiles = tileset.tileset().get(type);
        if (tiles.size() == 1) {
            return tiles.get(0);
        }

        return tiles.get(RANDOM.nextInt(2));
    }

}
