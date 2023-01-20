package org.eam.games.wanderer.world.tile;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.eam.games.wanderer.drawable.WithImage;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
class TilesetMetadata implements WithImage {

    @Nonnull
    private String tilesetFilename;
    private int tileSize;
    @Nonnull
    private List<TileMetadata> tiles;

    @SneakyThrows
    List<TileView> tiles() {
        BufferedImage tilesetImage = fromResource(tilesetFilename);

        return tiles.stream()
            .map(m -> toTileView(tilesetImage, m))
            .collect(Collectors.toList());
    }

    private TileView toTileView(BufferedImage tilesetImage, TileMetadata metadata) {
        BufferedImage image = tilesetImage.getSubimage(metadata.getCol() * tileSize, metadata.getRow() * tileSize,
            tileSize, tileSize);
        return TileView.from(image, metadata);
    }

}
