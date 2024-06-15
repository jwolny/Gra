package com.mygdx.game.model.MapTools;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class MapHelper {
    private TiledMap map;
    private MapLoader mapLoader;
    private MapObjectParser mapObjectParser;

    public MapHelper(MapLoader mapLoader, MapObjectParser mapObjectParser) {
        this.mapLoader = mapLoader;
        this.mapObjectParser = mapObjectParser;
    }

    public OrthogonalTiledMapRenderer setUpMap(String mapPath, World world) {
        map = mapLoader.loadMap(mapPath);
        mapObjectParser.parseMapObjects(map.getLayers().get("Objects").getObjects(), world);
        return new OrthogonalTiledMapRenderer(map);
    }

    public void dispose() {
        if (map != null) {
            map.dispose();
        }
    }
}
