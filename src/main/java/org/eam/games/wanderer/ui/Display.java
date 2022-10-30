package org.eam.games.wanderer.ui;

import com.google.common.base.Stopwatch;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.drawable.Drawable;
import org.eam.games.wanderer.engine.Camera;
import org.eam.games.wanderer.properties.GameProperties;

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

    /**
     * Initially paints the whole game, and repaints by timer.
     */
    @Override
    public void paintComponent(Graphics g) {
        Stopwatch started = Stopwatch.createStarted();
        super.paintComponent(g);
        drawGame(g);

        // baseline was 0-1 millis
        log.trace("World painted: elapsedMs={}", started.elapsed(TimeUnit.MILLISECONDS));
    }

    private void drawGame(Graphics g) {
//        g = camera.translate(g);

        world.draw(g);
        hero.draw(g);

//        for (int i = 0; i < controller.getMonsterList().size(); i++) {
//            drawCharacter.setCharacter(controller.getMonster(i));
//            drawCharacter.draw(g);
//        }
//
//        drawCharacter.setCharacter(controller.getHero());
//        drawCharacter.draw(g);
//
//        g.translate(-offSetX, -offSetY);
//        hud.draw(g);
    }

}
