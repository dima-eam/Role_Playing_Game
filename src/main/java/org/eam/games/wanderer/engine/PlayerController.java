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

    private final PlayerMovement playerMovement;
    private final World world;
    private final Monsters monsters;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> processMove(Direction.LEFT);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> processMove(Direction.RIGHT);
            case KeyEvent.VK_UP, KeyEvent.VK_W -> processMove(Direction.UP);
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> processMove(Direction.DOWN);
            default -> {
            }
        }
    }

    private void processMove(Direction direction) {
        playerMovement.move(direction, world);
        monsters.react();
    }

}

