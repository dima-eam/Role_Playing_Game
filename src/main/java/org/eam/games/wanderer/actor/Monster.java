package org.eam.games.wanderer.actor;

import java.awt.Image;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import org.eam.games.wanderer.world.Cell;
import org.eam.games.wanderer.world.World;
import org.eam.games.wanderer.world.Tile;

public class Monster extends Actor {

    private static final String MONSTER_IMAGE = Tile.class.getResource("/images/monster.gif").getFile();
    private static final ImageIcon ICON = new ImageIcon(MONSTER_IMAGE);
    private boolean moveLastRound;

    public Monster(Cell cell, int level) {
        super(1,1,1);
//        this.level = level;
//        maxHealthPoint = 10 * level * rollDice();
//        defendPoint = (int) Math.ceil(level * rollDice() / 2.0);
//        strikePoint = level * rollDice();
//        healthPoint = maxHealthPoint;
        initCharacter(cell);
    }

    public void move(World world) {
        int newX;
        int newY;

//        do {
//            newX = x + stepRandom();
//            newY = y + stepRandom();
//        } while (world.getTile(newX, newY).map(Tile::isSolid).orElse(true));
//
//        x = newX;
//        y = newY;
    }

    public void takeTurn(World world) {
        if (!moveLastRound) {
            move(world);
            moveLastRound = true;
        } else {
            moveLastRound = false;
        }
    }

    private int stepRandom() {
        int r = new Random().nextInt(3);
        return r - 1;
    }

    void initCharacter(Cell cell) {
        ImageIcon icon = new ImageIcon(MONSTER_IMAGE);
//        image = icon.getImage();
//       cell == cell;
    }

    @Override
    Map<Direction, Image> imagesByDirection() {
        return null;
    }
}
