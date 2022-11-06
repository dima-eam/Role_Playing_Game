package org.eam.games.wanderer.actor;

import static org.eam.games.wanderer.engine.Dice.rollDice;

import java.awt.Image;
import java.util.Map;

/**
 * Represents AI controlled enemy.
 */
public class Monster extends Actor {

    private static final String MONSTER_IMAGE = "/images/monster.gif";

    private final Map<Direction, Image> imagesByDirection = Map.of(
        Direction.RIGHT, fromFilename(MONSTER_IMAGE),
        Direction.LEFT, fromFilename(MONSTER_IMAGE),
        Direction.UP, fromFilename(MONSTER_IMAGE),
        Direction.DOWN, fromFilename(MONSTER_IMAGE)
    );

    public Monster(int level) {
        super(10 * level * rollDice(), (int) Math.ceil(level * rollDice() / 2.0), level * rollDice());
        this.level = level;
        healthPoint = maxHealthPoint;
    }

    @Override
    public String stats() { // todo reveal stats based on hero's level or skill check
        return "Monster(" + level + ")" + " | HP: " + healthPoint + "/" + maxHealthPoint;
    }

    @Override
    Map<Direction, Image> imagesByDirection() {
        return imagesByDirection;
    }

}
