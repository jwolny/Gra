package com.mygdx.game.model.MapTools;

import com.badlogic.gdx.maps.tiled.TiledMap;

public interface MapLoader {
    TiledMap loadMap(String mapPath);
}

