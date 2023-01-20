package org.eam.games.wanderer.world.tile;

import java.awt.image.BufferedImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.world.tile.OrientedTileset.TileType;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class TileView {

    private final BufferedImage image;
    private final TileMetadata metadata;

    static TileView from(BufferedImage image, TileMetadata metadata) {
        return new TileView(image, metadata);
    }

    TileType type() {
        return TileType.from(metadata.getDescriptor());
    }

    Tile toTile(boolean solid) {
        return new Tile(image, true); // todo solidity from metadata
    }


}
