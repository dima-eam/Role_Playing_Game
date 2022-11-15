package org.eam.games.wanderer.actor;

/**
 * Provides some simple statistics to be displayed on HUD.
 */
public interface WithStats {

    default String stats() {
        return "";
    }

}
