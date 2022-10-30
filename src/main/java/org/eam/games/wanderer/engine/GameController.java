package org.eam.games.wanderer.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.actor.BossMonster;
import org.eam.games.wanderer.actor.Player;
import org.eam.games.wanderer.actor.Direction;
import org.eam.games.wanderer.world.Cell;
import org.eam.games.wanderer.world.World;
import org.eam.games.wanderer.actor.Monster;

/**
 * Processes key events and controls game entity reactions on this events.
 */
@Log4j2
public class GameController extends KeyAdapter {

    private static final int INITIAL_MONSTER_AMOUNT = 20;
    private Player player;
    private List<Monster> monsterList;
    private int monsterAmount;
    private Monster keyMonster;
    private BossMonster boss;


    private World world;

    public GameController() {
        monsterAmount = INITIAL_MONSTER_AMOUNT;
        world = new World(35, 29);
        player = new Player();
        initGame();
    }

    private void initGame() {
        monsterList = new ArrayList<>();
        Cell monsterCell;

//        for (int i = 0; i < monsterAmount - 2; i++) {
//            monsterCell = world.findEmptyTile();
//            monsterList.add(new Monster(monsterCell, player.level));
//        }
//
//        monsterCell = findEmptyTile();
//        keyMonster = new Monster(monsterCell[0], monsterCell[1], player.level);
//
//        monsterCell = findEmptyTile();
//        boss = new BossMonster(monsterCell[0], monsterCell[1], player.level);
//
//        monsterList.add(keyMonster);
//        monsterList.add(boss);
    }

    public Player getHero() {
        return player;
    }

    public Monster getMonster(int i) {
        return monsterList.get(i);
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public World getMaze() {
        return world;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        boolean heroMoved = false;

        switch (key) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
            case KeyEvent.VK_SPACE:
                for (int i = 0; i < monsterList.size(); i++) {
                    if (false){//(monsterList.get(i).x == player.x && monsterList.get(i).y == player.y) {
                        player.attack(monsterList.get(i));
                        if (monsterList.get(i).dead()) {
                            player.getStronger();
                            monsterList.remove(i);
                        } else {
                            monsterList.get(i).attack(player);
                        }
                    }
                }
                break;
            default:
        }

        if (heroMoved) {
            for (Monster monster : monsterList) {
                monster.takeTurn(world);
            }
        }

//        if ((keyMonster.dead() && boss.dead()) || monsterList.isEmpty()) {
//            nextLevel();
//        }
    }

    private void nextLevel() {
        Random r = new Random();
//        player.updateLevel();
        player.levelUp();
        monsterAmount = r.nextInt(5) + INITIAL_MONSTER_AMOUNT;
        initGame();
    }

}

