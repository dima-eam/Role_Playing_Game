package org.eam.games.wanderer.properties;

import java.awt.Dimension;
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

    private static final int DEFAULT_WIDTH_IN_TILES = 35;
    private static final int DEFAULT_HEIGHT_IN_TILES = 29;
    private static final int DEFAULT_TILE_SIZE = 72;
    /**
     * Screen repaint interval, consumed by the timer. Timer will fire its events calling actionPerformed() method.
     */
    private static final int INTERVAL = 50;

    @Nonnull
    private final Dimension screenSize;
    private final int widthInTiles;
    private final int heightInTiles;
    private final int boundLeft;
    private final int boundRight;
    private final int boundTop;
    private final int boundBottom;
    private final int tileSize;
    private final int interval;

    /**
     * Creates properties instance with default tiles count and tiles size.
     */
    public static GameProperties from(Dimension screenSize) {
        int boundLeft = screenSize.width / 2;
        int boundRight = DEFAULT_HEIGHT_IN_TILES * DEFAULT_TILE_SIZE - screenSize.width / 2;
        int boundTop = screenSize.height / 2;
        int boundBottom = DEFAULT_HEIGHT_IN_TILES * DEFAULT_TILE_SIZE - screenSize.height / 2;

        return GameProperties.builder()
            .screenSize(screenSize)
            .widthInTiles(DEFAULT_WIDTH_IN_TILES)
            .heightInTiles(DEFAULT_HEIGHT_IN_TILES)
            .boundLeft(boundLeft)
            .boundRight(boundRight)
            .boundBottom(boundBottom)
            .boundTop(boundTop)
            .tileSize(DEFAULT_TILE_SIZE)
            .interval(INTERVAL)
            .build();
    }

}
