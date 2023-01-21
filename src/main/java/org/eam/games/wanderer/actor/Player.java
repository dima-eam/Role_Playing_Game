package org.eam.games.wanderer.actor;

import static org.eam.games.wanderer.engine.Dice.rollDice;

import java.awt.Image;
import java.util.Map;
import org.eam.games.wanderer.engine.tile.HeroTiles;

/**
 * Game character, controlled by player.
 */
public class Player extends Actor {

    private static final HeroTiles TILES = new HeroTiles();

    public Player() { // todo externalize
        super(20 + 3 * rollDice(), 2 * rollDice(), 5 + rollDice());
    }

    @Override
    Map<Direction, Image> imagesByDirection() {
        return TILES.imagesByDirection();
    }

    @Override
    public String stats() {
        return "Hero(" + level + ")" + // todo hero name input
            " | HP: " + healthPoint + "/" + maxHealthPoint +
            " | ATK: " + strikePoint +
            " | DEF: " + defendPoint;
    }

    public void levelUp() {
        int chance = rollDice(10);
//        if (chance == 1) {
//            healthPoint = maxHealthPoint;
//        } else if (chance <= 4) {
//            setHealthPoint(healthPoint + healthPoint / 3);
//        } else {
//            setHealthPoint(healthPoint + healthPoint / 10);
//        }
    }

}


