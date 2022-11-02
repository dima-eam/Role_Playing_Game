package org.eam.games.wanderer.world;

import java.awt.Image;
import lombok.ToString;
import org.eam.games.wanderer.drawable.WithImage;

/**
 * Simple representation of a tile, as its image and some properties for interaction with it.
 */
@ToString(exclude = {"tileImage"})
public class Tile implements WithImage {

    private final Image tileImage;
    private final boolean isSolid;

    public Tile(String filename, boolean isSolid) {
        this.isSolid = isSolid;

        tileImage = fromFilename(filename);
    }

    public Image getTileImage() {
        return tileImage;
    }

    public boolean isSolid() {
        return isSolid;
    }

}
