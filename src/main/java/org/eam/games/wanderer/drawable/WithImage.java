package org.eam.games.wanderer.drawable;

import java.awt.Image;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;

/**
 * Provides common methods for displayable entities.
 */
public interface WithImage {

    @SneakyThrows
    default Image fromFilename(String filename) {
        return ImageIO.read(WithImage.class.getResource(filename));
    }

}
