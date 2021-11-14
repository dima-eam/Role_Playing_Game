package main.models;

import java.util.Random;

public class Hero extends Character {

  private static final String FACE_DOWN = Tile.class.getResource("/images/hero-down.gif").getFile();
  private static final String FACE_RIGHT = Tile.class.getResource("/images/hero-right.gif")
      .getFile();
  private static final String FACE_LEFT = Tile.class.getResource("/images/hero-left.gif").getFile();
  private static final String FACE_UP = Tile.class.getResource("/images/hero-up.gif").getFile();

  public enum Directions {
    UP, DOWN, LEFT, RIGHT;
  }

  public Hero() {
    maxHealthPoint = 20 + 3 * rollDice();
    defendPoint = 2 * rollDice();
    strikePoint = 5 + rollDice();
    healthPoint = maxHealthPoint;
    initCharacter();
  }

  public void initCharacter() {
    setImage(FACE_DOWN);
    x = 0;
    y = 0;
  }

  public void move(Maze maze, Directions dir) {
    int newX = x;
    int newY = y;

    switch (dir) {
      case UP -> {
        newY = y - 1;
        setImage(FACE_UP);
      }
      case DOWN -> {
        newY = y + 1;
        setImage(FACE_DOWN);
      }
      case LEFT -> {
        newX = x - 1;
        setImage(FACE_LEFT);
      }
      case RIGHT -> {
        newX = x + 1;
        setImage(FACE_RIGHT);
      }
    }

    if (!maze.getTile(newX, newY).orElse(Maze.WALL).isSolid() && !this.isDead()) {
      x = newX;
      y = newY;
    }

    if (maze.getTile(newX, newY).orElse(Maze.WALL).isSolid()) {
      //do nothing
    }

  }

  public void levelUp() {
    int chance = new Random().nextInt(10);
    if (chance == 0) {
      healthPoint = maxHealthPoint;
    } else if (chance <= 4) {
      setHealthPoint(healthPoint + healthPoint / 3);
    } else {
      setHealthPoint(healthPoint + healthPoint / 10);
    }
  }

}


