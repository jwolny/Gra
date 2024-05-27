package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.viewmodel.BombListener;
import com.mygdx.game.viewmodel.PlayerViewModel;

import static com.mygdx.game.model.Constants.PPM;

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
