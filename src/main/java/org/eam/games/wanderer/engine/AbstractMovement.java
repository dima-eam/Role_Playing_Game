package org.eam.games.wanderer.engine;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
class AbstractMovement {

    @Getter
    protected final Position current;

    public int xOffset(int xOffset, int tileSize) {
        return current.xOffset(xOffset) * tileSize;
    }

    public int yOffset(int yOffset, int tileSize) {
        return current.yOffset(yOffset) * tileSize;
    }

}
