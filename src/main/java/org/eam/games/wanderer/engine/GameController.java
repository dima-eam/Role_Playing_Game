package org.eam.games.wanderer.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.actor.Monster;

/**
 * Processes key events for the whole game, like exit, menus etc.
 */
@Log4j2
public class GameController extends KeyAdapter {


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        boolean heroMoved = false;

        switch (key) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
            case KeyEvent.VK_SPACE:
//                for (int i = 0; i < monsterList.size(); i++) {
//                    if (false) {//(monsterList.get(i).x == player.x && monsterList.get(i).y == player.y) {
//                        player.attack(monsterList.get(i));
//                        if (monsterList.get(i).dead()) {
//                            player.getStronger();
//                            monsterList.remove(i);
//                        } else {
//                            monsterList.get(i).attack(player);
//                        }
//                    }
//                }
//                break;
            default:
        }

//        if (heroMoved) {
//            for (Monster monster : monsterList) {
//                monster.takeTurn(world);
//            }
//        }

//        if ((keyMonster.dead() && boss.dead()) || monsterList.isEmpty()) {
//            nextLevel();
//        }
    }

    private void nextLevel() {
        Random r = new Random();
//        player.updateLevel();
//        monsterAmount = r.nextInt(5) + INITIAL_MONSTER_AMOUNT;
//        initGame();
    }

}

