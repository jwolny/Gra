package com.mygdx.game.model.BombTools;

import com.badlogic.gdx.physics.box2d.Body;

import com.badlogic.gdx.physics.box2d.World;

public class Bomb extends BombHelper {
    public Bomb(Body body, float x, float y, float radius, World world) {
        super(body, x, y, radius, world);
    }

    @Override
    public void update() {

    }

    private void dispose() {
    }

}
