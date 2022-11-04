package org.eam.games.wanderer.actor;

import java.awt.Image;
import java.util.Map;

public class BossMonster extends Monster {

    private static final String BOSS_IMAGE = "/images/boss.png";

    private final Map<Direction, Image> imagesByDirection = Map.of(
        Direction.RIGHT, fromFilename(BOSS_IMAGE),
        Direction.LEFT, fromFilename(BOSS_IMAGE),
        Direction.UP, fromFilename(BOSS_IMAGE),
        Direction.DOWN, fromFilename(BOSS_IMAGE)
    );

    public BossMonster(int level) {
        super(level);
//        maxHealthPoint = 2 * level * rollDice() + rollDice();
//        defendPoint = (int) Math.ceil(level / 2.0 * rollDice() + rollDice() / 2.0);
//        strikePoint = level * rollDice() + getLevel();
//        healthPoint = maxHealthPoint;
    }

    @Override
    Map<Direction, Image> imagesByDirection() {
        return imagesByDirection;
    }

}
