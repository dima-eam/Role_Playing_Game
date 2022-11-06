package org.eam.games.wanderer.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.actor.Actor;
import org.eam.games.wanderer.actor.BossMonster;
import org.eam.games.wanderer.actor.Monster;
import org.eam.games.wanderer.actor.WithStats;
import org.eam.games.wanderer.world.Cell;
import org.eam.games.wanderer.world.Tile;
import org.eam.games.wanderer.world.World;

public class Monsters implements WithStats {

    private static final int INITIAL_MONSTER_AMOUNT = 20;

    private final World world;
    private final List<MonsterMovement> monsters;
    private final int monsterAmount;

    private String stats = "";

    public Monsters(World world) {
        this.world = world;

        monsterAmount = INITIAL_MONSTER_AMOUNT;
        monsters = new ArrayList<>(monsterAmount);

        populateMonsters();
    }

    public void react() {
        for (MonsterMovement monster : monsters) {
            if (monster.monster.dead()) {
                continue;
            }

            Cell newCell;
            do {
                newCell = monster.nextCell();
            } while (world.getTile(newCell.getXTile(), newCell.getYTile())
                .map(Tile::isSolid)
                .orElse(true));

            monster.cell.move(newCell);
        }
    }

    public void forEach(BiConsumer<Cell, Actor> process) {
        monsters.stream()
            .filter(m -> !m.monster.dead())
            .forEach(m -> process.accept(m.cell, m.monster));
    }

    public List<Monster> forCell(Cell heroCell) {
        List<Monster> monstersInCell = monsters.stream()
            .filter(m -> m.cell.equals(heroCell))
            .map(m -> m.monster)
            .filter(m -> !m.dead())
            .collect(Collectors.toList());

        stats = monstersInCell.stream()
            .map(Monster::stats)
            .collect(Collectors.joining(", "));

        return monstersInCell;
    }

    @Override
    public String stats() {
        return stats;
    }

    private void populateMonsters() {
        for (int i = 0; i < monsterAmount - 2; i++) {
            monsters.add(new MonsterMovement(world.findEmptyTile(), new Monster(1)));
        }
        Monster keyMonster = new Monster(1);
        monsters.add(new MonsterMovement(world.findEmptyTile(), keyMonster));
        BossMonster boss = new BossMonster(2);
        monsters.add(new MonsterMovement(world.findEmptyTile(), boss));
    }

    @AllArgsConstructor
    private static class MonsterMovement {

        private final Cell cell;
        private final Monster monster;

        public Cell nextCell() {
            return switch (Dice.rollDice(5)) {
                case 1 -> cell.moveUp();
                case 2 -> cell.moveDown();
                case 3 -> cell.moveLeft();
                case 4 -> cell.moveRight();
                default -> cell;
            };
        }

    }

}
