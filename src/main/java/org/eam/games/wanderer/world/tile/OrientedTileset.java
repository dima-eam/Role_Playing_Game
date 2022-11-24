package org.eam.games.wanderer.world.tile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/**
 * Represents a set of tiles, which are oriented. That is, horizontal, vertical, angles etc. tiles to form walls, roads,
 * rivers and other similar structures. Such a tile belongs to one of {@link TileType} below, codified like that:
 * <pre>
 * UL H1 UR
 * V1 C  V2
 * LL H2 LR
 * </pre>
 * Where H1 and H2 are horizontal parts, which may form long structures, such as walls or roads, and V1 and V2 are
 * vertical parts, with same assumptions. Ul/UR/LL/LR are corners, upper-left etc. Also presented bot not mentioned
 * tiles are VM for vertical tile merged into horizontal, and HML/HMR for two ends of horizontal tile, merged into
 * vertical.
 */
public interface OrientedTileset {

    Map<TileType, List<Tile>> tileset();

    enum TileType {

        VERTICAL("v\\d"),
        HORIZONTAL("h\\d"),
        UPPER_LEFT("ul"),
        UPPER_RIGHT("ur"),
        LOWER_LEFT("ll"),
        LOWER_RIGHT("lr"),
        VERTICAL_MERGE("vm"),
        HORIZONTAL_MERGE_LEFT("hml"),
        HORIZONTAL_MERGE_RIGHT("hmr"),
        CENTER("c");

        private final String suffix;

        TileType(String suffix) {
            this.suffix = suffix;
        }

        static TileType from(@Nonnull String suffix) {
            return Arrays.stream(values())
                .filter(v -> suffix.matches(v.suffix))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Suffix not supported: " + suffix));
        }

    }

}
