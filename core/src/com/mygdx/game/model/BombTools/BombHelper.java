package com.mygdx.game.model.BombTools;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class BombHelper {
    protected Body body;
    protected float x, y, radius;
    protected World world;
    public BombHelper(Body body, float x, float y, float radius, World world) {
        this.body = body;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.world = world;
    }

    public Body getBody(){
        return body;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius(){
        return radius;
    }

    public World getWorld() {
        return world;
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }
}
