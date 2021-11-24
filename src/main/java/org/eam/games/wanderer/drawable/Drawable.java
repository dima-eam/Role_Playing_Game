package org.eam.games.wanderer.drawable;

import java.awt.Graphics;

/**
 * Main interface for any drawable entity in the game. Utilizes AWT {@link Graphics} context.
 */
public interface Drawable {

    /**
     * Passes the graphics context to draw/redraw an antity.
     */
    void draw(Graphics g);

}
