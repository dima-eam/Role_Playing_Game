package org.eam.games.wanderer.engine;

import java.awt.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.eam.games.wanderer.actor.Direction;
import org.eam.games.wanderer.actor.Monster;
import org.eam.games.wanderer.engine.tile.Tile;
import org.eam.games.wanderer.world.World;

@Getter
public class MonsterMovement extends AbstractMovement {

    private final Monster monster;
    private final World world;

    MonsterMovement(int tileSize, Monster monster, World world) {
        super(tileSize, Position.from(world.walkableCell()), Direction.DOWN);

        this.monster = monster;
        this.world = world;
    }

    public Image image() {
        return monster.imageForDirection(direction);
    }

    public void react() {
        Directional next;
        do {
            next = next();
        } while (world.tileFor(next.position.getXTile(), next.position.getYTile())
            .map(Tile::isSolid)
            .orElse(true));

        current.moveTo(next.position);
        direction = next.direction;
    }

    private Directional next() {
        return switch (Dice.rollDice(5)) {
            case 1 -> new Directional(current.up(), Direction.UP);
            case 2 -> new Directional(current.down(), Direction.DOWN);
            case 3 -> new Directional(current.left(), Direction.LEFT);
            case 4 -> new Directional(current.right(), Direction.RIGHT);
            default -> new Directional(current, direction);
        };
    }

    @AllArgsConstructor
    private static class Directional {

        private final Position position;
        private final Direction direction;

    }

}
