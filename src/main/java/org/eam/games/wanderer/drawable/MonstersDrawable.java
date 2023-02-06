package org.eam.games.wanderer.drawable;

import lombok.AllArgsConstructor;
import org.eam.games.wanderer.engine.Monsters;

@AllArgsConstructor
public class MonstersDrawable implements Drawable {

    private final Monsters monsters;

    @Override
    public void draw(GraphicsContext context) {
        monsters.forEach(m -> context.process(m.image(), m.xOffset(context.getXOffset()),
            m.yOffset(context.getYOffset())));
    }

}
