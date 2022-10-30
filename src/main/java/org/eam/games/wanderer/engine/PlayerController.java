package org.eam.games.wanderer.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.actor.Direction;
import org.eam.games.wanderer.world.World;

/**
 * Processes key events and controls player movements via setting player's position.
 */
@AllArgsConstructor
public class PlayerController extends KeyAdapter {

    private final Position playerPosition;
    private final World world;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> playerPosition.move(Direction.LEFT, world);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> playerPosition.move(Direction.RIGHT, world);
            case KeyEvent.VK_UP, KeyEvent.VK_W -> playerPosition.move(Direction.UP, world);
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> playerPosition.move(Direction.DOWN, world);
            default -> {
            }
        }
    }

}

