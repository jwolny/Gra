package com.mygdx.game.model.MapTools;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.physics.box2d.World;

public interface MapObjectParser {
    void parseMapObjects(MapObjects mapObjects, World world);
}

