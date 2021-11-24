package org.eam.games.wanderer.actor;

import static org.eam.games.wanderer.engine.Dice.rollDice;

import java.awt.Image;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.drawable.WithImage;

/**
 * Represents any acting ("alive") entity, such as monster, playable character ("hero") etc. Class fields are mutable,
 * to keep the balance between performance and maintainability, because creating new instance after each hit is way too
 * expensive. To keep control, though, no public accessors are used, and mutation happens only via small set of
 * methods.
 */
@AllArgsConstructor
public abstract class Actor implements WithImage {

    private int maxHealthPoint;
    private int healthPoint;
    private int defendPoint;
    private int strikePoint;
    private int level;

    public Actor(int maxHealthPoint, int defendPoint, int strikePoint) {
        this.maxHealthPoint = maxHealthPoint;
        this.defendPoint = defendPoint;
        this.strikePoint = strikePoint;
    }

    public void attack(Actor enemy) {
        int strikeValue = calculateStrikeValue();
        if (strikeValue > enemy.defendPoint) {
            enemy.healthPoint -= strikeValue - enemy.defendPoint;
        }
    }

    public boolean dead() {
        return healthPoint <= 0;
    }


    private int calculateStrikeValue() {
        return 2 * rollDice() + this.strikePoint;
    }

    public void getStronger() {
        maxHealthPoint += rollDice();
        defendPoint += rollDice();
        strikePoint += rollDice();
    }

    public Image imageForDirection(Direction direction) {
        return imagesByDirection().get(direction);
    }

    abstract Map<Direction, Image> imagesByDirection();

}
