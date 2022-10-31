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

    private final Movement playerMovement;
    private final World world;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> playerMovement.move(Direction.LEFT, world);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> playerMovement.move(Direction.RIGHT, world);
            case KeyEvent.VK_UP, KeyEvent.VK_W -> playerMovement.move(Direction.UP, world);
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> playerMovement.move(Direction.DOWN, world);
            default -> {
            }
        }
    }

}

