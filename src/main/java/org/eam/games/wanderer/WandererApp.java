package org.eam.games.wanderer;

import com.google.common.base.Stopwatch;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;
import org.eam.games.wanderer.actor.Actor;
import org.eam.games.wanderer.actor.Player;
import org.eam.games.wanderer.drawable.Drawable;
import org.eam.games.wanderer.drawable.MonstersDrawable;
import org.eam.games.wanderer.drawable.PlayerDrawable;
import org.eam.games.wanderer.drawable.WorldDrawable;
import org.eam.games.wanderer.engine.Camera;
import org.eam.games.wanderer.engine.CombatController;
import org.eam.games.wanderer.engine.GameController;
import org.eam.games.wanderer.engine.Monsters;
import org.eam.games.wanderer.engine.PlayerController;
import org.eam.games.wanderer.engine.PlayerMovement;
import org.eam.games.wanderer.properties.GameProperties;
import org.eam.games.wanderer.ui.Display;
import org.eam.games.wanderer.ui.Game;
import org.eam.games.wanderer.ui.Hud;
import org.eam.games.wanderer.world.World;
import org.eam.games.wanderer.world.room.RoomsWorld;

/**
 * Bootstrapping game in AWT {@link EventQueue}. Creates and run game subsystems, such as hero, monsters, and game
 * world.
 */
@Log4j2
public class WandererApp {

    public static void main(String[] args) {
        Stopwatch started = Stopwatch.createStarted();

        EventQueue.invokeLater(WandererApp::init);

        log.info("Application has started: elapsedMs={}", () -> started.stop().elapsed(TimeUnit.MILLISECONDS));
    }

    /**
     * Creates and wires all game components. Each component is an abstraction of an entity, such as player, or world
     * terrain. Interaction between components is encapsulated in additional classes, based on its functionality, e.g.
     * moving a hero in the world.
     */
    private static void init() {
        GameProperties properties = GameProperties.defaults();

        World world = new RoomsWorld();
//        World world = new MazeWorld(properties.getWidthInTiles(), properties.getHeightInTiles());

        Drawable worldDrawable = new WorldDrawable(properties, world);
        Actor hero = new Player();
        PlayerMovement player = PlayerMovement.start(properties.getTileSize(), world);
        Drawable drawHero = new PlayerDrawable(hero, properties.getTileSize(), player);
        Camera camera = new Camera(player, world, properties);
        Monsters monsters = new Monsters(properties, world);
        Hud hud = new Hud(hero, player, monsters);
        MonstersDrawable monstersDrawable = new MonstersDrawable(monsters);
        Display display = Display.from(camera, worldDrawable, drawHero, hud, monstersDrawable);

        Game.run(properties, display, List.of(new GameController(), new PlayerController(player, monsters),
            hud), List.of(new CombatController(player.getCurrent(), hero, monsters, hud)));

        log.info("Game initialized: properties={}", properties);
    }

}
