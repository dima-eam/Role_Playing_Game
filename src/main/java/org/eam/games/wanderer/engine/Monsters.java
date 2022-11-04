package org.eam.games.wanderer.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import lombok.AllArgsConstructor;
import org.eam.games.wanderer.actor.Actor;
import org.eam.games.wanderer.actor.BossMonster;
import org.eam.games.wanderer.actor.Monster;
import org.eam.games.wanderer.world.Cell;
import org.eam.games.wanderer.world.Tile;
import org.eam.games.wanderer.world.World;

public class Monsters {

    private static final int INITIAL_MONSTER_AMOUNT = 20;

    private final World world;
    private final List<MonsterInCell> monsterList;
    private final int monsterAmount;

    public Monsters(World world) {
        this.world = world;

        monsterAmount = INITIAL_MONSTER_AMOUNT;
        monsterList = new ArrayList<>(monsterAmount);

        populateMonsters();
    }

    public void react() {
        for (MonsterInCell monster : monsterList) {
            Cell newCell;
            do {
                newCell = monster.nextCell();
            } while (world.getTile(newCell.getXTile(), newCell.getYTile())
                .map(Tile::isSolid)
                .orElse(true));

            monster.cell.move(newCell);
        }
    }

    private void populateMonsters() {
        for (int i = 0; i < monsterAmount - 2; i++) {
            monsterList.add(new MonsterInCell(world.findEmptyTile(), new Monster(1)));
        }
        Monster keyMonster = new Monster(1);
        monsterList.add(new MonsterInCell(world.findEmptyTile(), keyMonster));
        BossMonster boss = new BossMonster(2);
        monsterList.add(new MonsterInCell(world.findEmptyTile(), boss));
    }

    public void forEach(BiConsumer<Cell, Actor> process) {
        monsterList.forEach(m -> process.accept(m.cell, m.monster));
    }

    @AllArgsConstructor
    private static class MonsterInCell {

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
