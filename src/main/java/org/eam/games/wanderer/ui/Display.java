package org.eam.games.wanderer.ui;

import com.google.common.base.Stopwatch;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.drawable.Drawable;
import org.eam.games.wanderer.drawable.GraphicsContext;
import org.eam.games.wanderer.engine.Camera;

/**
 * Controls all graphics in the game, utilizing {@link javax.swing.JComponent#paintComponent(Graphics)} functionality,
 * passing {@link Graphics} instances to every drawable object to update.
 */
@Log4j2
@AllArgsConstructor
public class Display extends JPanel {

    private final Camera camera;
    private final Drawable world;
    private final Drawable hero;
    private final Drawable hud;
    private final Drawable monsters;

    /**
     * Initially paints the whole game, and repaints by timer.
     */
    @Override
    public void paintComponent(Graphics g) {
        Stopwatch started = Stopwatch.createStarted();
        super.paintComponent(g);
        drawGame(g);

        // baseline was 0-1 millis
        log.trace("World painted: elapsedMs={}", () -> started.elapsed(TimeUnit.MILLISECONDS));
    }

    private void drawGame(Graphics graphics) {
        GraphicsContext context = camera.moved(graphics);

        world.draw(context); // todo make a collection?
        hero.draw(context);
        hud.draw(context);
        monsters.draw(context);
    }

}
