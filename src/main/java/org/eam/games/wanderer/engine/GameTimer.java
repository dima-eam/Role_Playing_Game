package org.eam.games.wanderer.engine;

import com.google.common.base.Stopwatch;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.properties.GameProperties;
import org.eam.games.wanderer.ui.Display;

/**
 * Starts game loop and periodically notifies the world to repaint itself, eventually calling
 * {@link Display#paintComponent(Graphics)} method.
 */
@Log4j2
public class GameTimer implements ActionListener {

    private final Component display;
    private final Timer timer;

    private GameTimer(GameProperties properties, Component display) {
        this.display = display;

        timer = new Timer(properties.getInterval(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Stopwatch started = Stopwatch.createStarted();

        display.repaint();

        log.trace("Redrawn on timer: elapsedMs={}", started.elapsed(TimeUnit.MILLISECONDS));
    }

    public static void start(GameProperties properties, Display display) {
        GameTimer gameTimer = new GameTimer(properties, display);
        gameTimer.timer.start();
    }

}
