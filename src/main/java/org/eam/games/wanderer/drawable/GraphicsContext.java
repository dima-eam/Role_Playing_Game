package org.eam.games.wanderer.drawable;

import java.awt.Graphics;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Carries over {@link Graphics} instance along with offset from zero point (0, 0) measured in tiles. This allows to
 * translate the context according to the offset, and draw only visible part of the game world.
 */
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"graphics"})
public class GraphicsContext {

    private final Graphics graphics;
    @Getter
    private final int xOffset;
    @Getter
    private final int yOffset;

    public static GraphicsContext from(Graphics graphics, int xOffset, int yOffset) {
        return new GraphicsContext(graphics, xOffset, yOffset);
    }

    /**
     * Process the graphics context, enclosed in caller's execution context.
     */
    public void process(Consumer<Graphics> logic) {
        logic.accept(graphics);
    }

}
