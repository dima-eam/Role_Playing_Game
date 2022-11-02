package org.eam.games.wanderer.properties;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Stores application params. todo config file mapping.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class GameProperties {

    private static final int DEFAULT_WIDTH_IN_TILES = 30;
    private static final int DEFAULT_HEIGHT_IN_TILES = 29;
    private static final int DEFAULT_TILE_SIZE = 72;
    /**
     * Screen repaint interval, consumed by the timer. Timer will fire its events calling actionPerformed() method.
     */
    private static final int INTERVAL = 50;

    @Nonnull
    private final Dimension screenSize;
    /**
     * World width
     */
    private final int widthInTiles;
    /**
     * World height
     */
    private final int heightInTiles;
    /**
     * Screen width in tiles, according to screen resolution
     */
    private final int boundRight;
    /**
     * Screen height in tiles, according to screen resolution
     */
    private final int boundBottom;
    private final int tileSize;
    private final int interval;

    /**
     * Creates properties instance with default tiles count and tiles size.
     */
    public static GameProperties defaults() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int boundRight = 1 + screenSize.width / DEFAULT_TILE_SIZE;
        int boundBottom = screenSize.height / DEFAULT_TILE_SIZE;

        return GameProperties.builder()
            .screenSize(screenSize)
            .widthInTiles(DEFAULT_WIDTH_IN_TILES)
            .heightInTiles(DEFAULT_HEIGHT_IN_TILES)
            .boundRight(boundRight)
            .boundBottom(boundBottom)
            .tileSize(DEFAULT_TILE_SIZE)
            .interval(INTERVAL)
            .build();
    }

    public int xOffset(int xTile) {
        return offset(xTile, boundRight / 2, widthInTiles);
    }

    public int yOffset(int yTile) {
        return offset(yTile, boundBottom / 2, heightInTiles);
    }

    /**
     * Evaluates if correct coordinate is between 0 and threshold, returns 0 while within threshold. When the coordinate
     * is above the threshold, return the offset, allowing scrolling through the game world.
     */
    private static int offset(int coordinate, int threshold, int boundary) {
        if (coordinate < threshold) {
            return 0;
        }
        if (coordinate < boundary - threshold) {
            return coordinate - threshold;
        }

        return boundary - 2 * threshold - 1;// todo fix extra row and col
    }
}
