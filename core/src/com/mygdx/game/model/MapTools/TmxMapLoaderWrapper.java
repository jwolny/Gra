package com.mygdx.game.model.MapTools;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class TmxMapLoaderWrapper implements MapLoader {
    @Override
    public TiledMap loadMap(String mapPath) {
        return new TmxMapLoader().load(mapPath);
    }
}
