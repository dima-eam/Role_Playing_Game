package org.eam.games.wanderer.drawable;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Provides common methods for displayable entities.
 */
public interface WithImage {

    default Image fromFilename(String filename) {
        ImageIcon icon = new ImageIcon(WithImage.class.getResource(filename).getFile());

        return icon.getImage();
    }

}
