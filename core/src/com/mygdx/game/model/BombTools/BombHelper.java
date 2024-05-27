package com.mygdx.game.model.BombTools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class BombHelper {
    protected Body body;
    protected float x, y, radius;
    protected Texture texture;
    protected Sound bombSound;
    protected World world;
    public BombHelper(Body body, float x, float y, float radius, World world) {
        this.body = body;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.bombSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bomboclat.mp3"));
        this.world = world;
    }
    public abstract void update();

    public Body getBody(){
        return body;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    public World getWorld() {
        return world;
    }
}
