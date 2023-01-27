package org.eam.games.wanderer.actor;

import static org.eam.games.wanderer.engine.Dice.rollDice;

import java.awt.Image;
import java.util.Map;
import org.eam.games.wanderer.engine.tile.MonsterTiles;

/**
 * Represents AI controlled enemy.
 */
public class Monster extends Actor {

    private static final MonsterTiles TILES = new MonsterTiles();

    public Monster() {
        super(10 * rollDice(), rollDice(), 2 * rollDice());
        healthPoint = maxHealthPoint;
    }

    @Override
    public String stats() {
        return "Monster(" + level + ")" + " | HP: " + healthPoint + "/" + maxHealthPoint;
    }

    @Override
    Map<Direction, Image> imagesByDirection() {
        return TILES.imagesByDirection();
    }

}
