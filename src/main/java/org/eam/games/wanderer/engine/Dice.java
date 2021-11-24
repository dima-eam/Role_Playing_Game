package org.eam.games.wanderer.engine;

import java.util.Random;

public class Dice {

    private static final Random RANDOM = new Random();

    public static int rollDice() {
        return RANDOM.nextInt(6) + 1;
    }

}
