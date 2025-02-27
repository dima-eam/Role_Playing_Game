package org.eam.games.wanderer.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.eam.games.wanderer.actor.Monster;
import org.eam.games.wanderer.actor.WithStats;
import org.eam.games.wanderer.world.World;

public class Monsters implements WithStats {

    private static final int COUNT = 40;


    private final Set<WithStats> stats = new HashSet<>();

    private final World world;
    private final List<MonsterMovement> monsters;
    private final int monsterAmount;

    public Monsters(World world) {
        this.world = world;

        monsterAmount = COUNT;
        monsters = new ArrayList<>(monsterAmount);

        populateMonsters();
    }

    public void react(Position player) {
        for (MonsterMovement monster : monsters) {
            if (monster.getMonster().dead()) {
                stats.remove(monster.getMonster());
                continue;
            }
            if (monster.getCurrent().equals(player)) {
                stats.add(monster.getMonster());
            }

            monster.react();
        }
    }

    public void forEach(Consumer<MonsterMovement> process) {
        monsters.stream()
            .filter(m -> !m.getMonster().dead())
            .forEach(process);
    }

    public List<Monster> forCell(Position position) {
        return monsters.stream()
            .filter(m -> m.getCurrent().equals(position))
            .map(MonsterMovement::getMonster)
            .filter(m -> !m.dead())
            .collect(Collectors.toList());
    }

    @Override
    public String stats() {
        return stats.stream()
            .map(WithStats::stats)
            .collect(Collectors.joining(","));
    }

    private void populateMonsters() {
        for (int i = 0; i < monsterAmount; i++) {
            monsters.add(new MonsterMovement(new Monster(), world));
        }
//        Monster keyMonster = new Monster(1);
//        monsters.add(new MonsterMovement(world.findEmptyTile(), keyMonster));
//        BossMonster boss = new BossMonster(2);
//        monsters.add(new MonsterMovement(world.findEmptyTile(), boss));
    }

    public void getStronger() {
        for (MonsterMovement monster : monsters) {
            if (monster.getMonster().dead()) {
                continue;
            }
            monster.getMonster().levelUp();
        }
    }

    public void reset() {
        monsters.clear();

        populateMonsters();
    }

}
