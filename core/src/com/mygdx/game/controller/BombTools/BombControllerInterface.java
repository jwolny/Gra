package com.mygdx.game.controller.BombTools;

import com.badlogic.gdx.physics.box2d.World;

public interface BombControllerInterface {
    void explode(float x, float y, World world);
}
