package org.eam.games.wanderer.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.actor.Actor;
import org.eam.games.wanderer.actor.Monster;
import org.eam.games.wanderer.ui.Hud;
import org.eam.games.wanderer.world.Cell;

@Log4j2
@AllArgsConstructor
public class CombatController extends KeyAdapter {

    private final Cell heroCell;
    private final Actor hero;
    private final Monsters monsters;
    private final Hud hud;

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                monsters.forCell(heroCell)
                    .forEach(m -> combatRound(hero, m));
                break;
        }
    }

    private void combatRound(Actor hero, Monster monster) {
        log.info("Combat round: hero={}, monster={}", hero, monster);
        hero.attack(monster);
        monster.attack(hero);

        if (hero.dead()) {
            log.info("Game over");
            hud.gameOver();
        }

        if (monster.dead()) {
            log.info("Monster killed, getting stronger");
            hero.getStronger();
            monsters.getStronger();
        }
    }

}
