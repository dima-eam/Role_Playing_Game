package org.eam.games.wanderer.engine;

import java.awt.Image;
import lombok.Getter;
import org.eam.games.wanderer.actor.Direction;
import org.eam.games.wanderer.actor.Monster;

@Getter
public class MonsterMovement extends AbstractMovement {

    private final Monster monster;

    MonsterMovement(Position current, Monster monster) {
        super(current, Direction.UP);

        this.monster = monster;
    }

    Position next() {
        return switch (Dice.rollDice(5)) {
            case 1 -> current.up();
            case 2 -> current.down();
            case 3 -> current.left();
            case 4 -> current.right();
            default -> current;
        };
    }

    public Image image() {
        return monster.imageForDirection(direction);
    }

}
