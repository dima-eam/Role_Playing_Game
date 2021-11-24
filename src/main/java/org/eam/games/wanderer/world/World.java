package org.eam.games.wanderer.world;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import lombok.extern.log4j.Log4j2;

/**
 * Represents game world (or, "labyrinth"), randomly generated at instantiation from two possible tiles: wall, and
 * grass. Generated structure is typically bigger than one screen, so drawing system must take care of not drawing
 * images, which are beyond the screen.
 */
@Log4j2
public final class World {

    private static final Random RANDOM = new Random();
    private static final Tile WALL = new Tile("/images/wall.jpg", true);
    private static final Tile GRASS = new Tile("/images/grass.png", false);

    private final Map<Cell, Tile> layout = new HashMap<>();

    private final int widthInTiles;
    private final int heightInTiles;

    public World(int widthInTiles, int heightInTiles) {
        this.widthInTiles = widthInTiles;
        this.heightInTiles = heightInTiles;

        generate();
    }

    /**
     * Returns an optional tile for building a path for a monster, or for other checks.
     */
    public Optional<Tile> getTile(int xTile, int yTile) { // todo pass Cell and move checks inside it
        if (xTile < 0 || xTile > widthInTiles - 1 || yTile < 0 || yTile > heightInTiles - 1) {
            return Optional.empty();
        } else { // todo intern cells to not create them every call
            return Optional.of(layout.get(new Cell(xTile, yTile)));
        }
    }

    public Cell findEmptyTile() {
        int x;
        int y;

        do {
            x = RANDOM.nextInt(widthInTiles);
            y = RANDOM.nextInt(heightInTiles);
        } while (getTile(x, y).get().isSolid());

        return new Cell(x, y);
    }

    private void generate() {
        for (int x = 0; x < widthInTiles; x++) {
            for (int y = 0; y < heightInTiles; y++) {
                layout.put(new Cell(x, y), WALL);
            }
        }

        layout.put(new Cell(0, 0), GRASS);
        carve(0, 0);
    }

    private void carve(int x, int y) {
        int[] moveX = {1, -1, 0, 0};
        int[] moveY = {0, 0, 1, -1};

        int dir = RANDOM.nextInt(4);
        int count = 0;

        while (count < 4) {
            int x1 = x + moveX[dir];
            int y1 = y + moveY[dir];
            int x2 = x1 + moveX[dir];
            int y2 = y1 + moveY[dir];
            if (getTile(x2, y2).orElse(GRASS).isSolid()) {
                layout.put(new Cell(x1, y1), GRASS);
                layout.put(new Cell(x2, y2), GRASS);
                carve(x2, y2);
            } else {
                dir = (dir + 1) % 4;
                count += 1;
            }
        }
    }

    public Optional<Cell> possbileMove(Cell newCell) {
        return getTile(newCell.getXTile(), newCell.getYTile())
            .filter(t -> !t.isSolid())
            .map(t -> newCell);
    }
}

