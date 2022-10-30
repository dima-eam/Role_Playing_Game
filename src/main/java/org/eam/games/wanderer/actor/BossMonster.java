package org.eam.games.wanderer.actor;

import javax.swing.ImageIcon;
import org.eam.games.wanderer.world.Cell;
import org.eam.games.wanderer.world.Tile;

public class BossMonster extends Monster {

    private static final String BOSS_IMAGE = Tile.class.getResource("/images/boss.png").getFile();

    public BossMonster(int xc, int yc, int level) {
        super(new Cell(xc,yc), level);
//        maxHealthPoint = 2 * level * rollDice() + rollDice();
//        defendPoint = (int) Math.ceil(level / 2.0 * rollDice() + rollDice() / 2.0);
//        strikePoint = level * rollDice() + getLevel();
//        healthPoint = maxHealthPoint;
        initCharacter(xc, yc);
    }

    void initCharacter(int xPos, int yPos) {
        ImageIcon icon = new ImageIcon(BOSS_IMAGE);
//        image = icon.getImage();
    }

}
