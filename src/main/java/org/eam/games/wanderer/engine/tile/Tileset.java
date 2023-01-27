package org.eam.games.wanderer.engine.tile;

import java.util.List;
import java.util.Map;

public interface Tileset {

    Map<TileType, List<Tile>> tileset();

}
