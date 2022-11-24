package org.eam.games.wanderer.world.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.world.Cell;
import org.eam.games.wanderer.world.World;
import org.eam.games.wanderer.world.tile.OrientedTileset;
import org.eam.games.wanderer.world.tile.Tile;
import org.eam.games.wanderer.world.tile.Tiles;
import org.eam.games.wanderer.world.tile.TilesetFromFile;

/**
 * Represents game world as sequence of randomly generated rooms. There is an external boundary, which may be considered
 * as room, too.
 */
@Log4j2
public final class RoomsWorld implements World {

    private static final Random RANDOM = new Random();

    private final Tiles tiles = new Tiles(new TilesetFromFile("/tiles/tilesets/walls.yml"));
    //    private final Tiles tiles = new Tiles(new TilesetFromFolder("/tiles/wall"));
    private final Tile fill = new Tile("/tiles/fill-dark.png", true);

    /**
     * Each element (sub-list) emulates one column of tiles. Allows to access tiles by passing xTile and yTile
     * coordinates, respectively
     */
    private final List<List<Tile>> layout = new ArrayList<>();

    private final int widthInTiles;
    private final int heightInTiles;
    private final Cell start;

    public RoomsWorld() {
        Rooms generator = new Rooms(tiles);
        GeneratedRooms rooms = generator.generate();

        widthInTiles = rooms.getWidth();
        heightInTiles = rooms.getHeight();
        start = new Cell( Math.abs(rooms.getWorldStart().getX()) + 1,
            Math.abs(rooms.getWorldStart().getY()) + 1);

        fillLayout(rooms);
    }

    @Override
    public Optional<Tile> tileForCell(Cell newCell) {
        if (newCell.within(widthInTiles, heightInTiles)) {
            return Optional.of(layout.get(newCell.getXTile()).get(newCell.getYTile()));
        }

        return Optional.empty();
    }

    @Override
    public Cell walkableCell() {
        Cell cell;
        do {
            int x = RANDOM.nextInt(widthInTiles);
            int y = RANDOM.nextInt(heightInTiles);
            cell = new Cell(x, y);
        } while (tileForCell(cell).orElse(tiles.nextTile(OrientedTileset.TileType.CENTER)).isSolid());

        return cell;
    }

    @Override
    public int widthInTiles() {
        return widthInTiles;
    }

    @Override
    public int heightInTiles() {
        return heightInTiles;
    }

    @Override
    public Cell start() {
        return start;
    }

    private void fillLayout(GeneratedRooms rooms) {
        // fill the whole world with background tile
        for (int x = 0; x < widthInTiles; x++) {
            List<Tile> col = new ArrayList<>();
            for (int y = 0; y < heightInTiles; y++) {
                col.add(fill);
            }
            layout.add(col);
        }

        // populate layout with shifting every coordinate down and right, to make upper-left room/cell to be at (0,0)
        for (Room room : rooms.getRooms()) {
            room.toLayout(rooms.getWorldStart(), layout);
        }
    }

}
