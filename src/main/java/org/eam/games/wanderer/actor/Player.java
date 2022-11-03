package org.eam.games.wanderer.actor;

import static org.eam.games.wanderer.engine.Dice.rollDice;

import java.awt.Image;
import java.util.Map;
import java.util.Random;

/**
 * Game character, controlled by player.
 */
public class Player extends Actor {

    private static final String FACE_DOWN = "/images/hero-down.gif";
    private static final String FACE_RIGHT = "/images/hero-right.gif";
    private static final String FACE_LEFT = "/images/hero-left.gif";
    private static final String FACE_UP = "/images/hero-up.gif";

    private final Map<Direction, Image> imagesByDirection = Map.of(
        Direction.RIGHT, fromFilename(FACE_RIGHT),
        Direction.LEFT, fromFilename(FACE_LEFT),
        Direction.UP, fromFilename(FACE_UP),
        Direction.DOWN, fromFilename(FACE_DOWN)
    );

    public Player() {
        super(20 + 3 * rollDice(), 2 * rollDice(), 5 + rollDice());
    }

    @Override
    Map<Direction, Image> imagesByDirection() {
        return imagesByDirection;
    }

    @Override
    public String stats() {
        return "Hero(" + level + ")" +
            "HP: " + healthPoint + "/" + maxHealthPoint +
            " | SP: " + strikePoint +
            " | DP: " + defendPoint;
    }

    public void levelUp() {
        int chance = new Random().nextInt(10);
//        if (chance == 0) {
//            healthPoint = maxHealthPoint;
//        } else if (chance <= 4) {
//            setHealthPoint(healthPoint + healthPoint / 3);
//        } else {
//            setHealthPoint(healthPoint + healthPoint / 10);
//        }
    }

}


