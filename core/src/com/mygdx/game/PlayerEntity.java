package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class PlayerEntity {
    protected float x, y, velX, velY, speed;
    protected float width, height;
    protected int up, down, left, right, bomb;
    protected World world;
    protected Body body;

    public PlayerEntity(float width, float height, Body body, int up, int down, int left, int right, int bomb, World world){
        this.width = width;
        this.height = height;
        this.body = body;
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.velX=0;
        this.velY=0;
        this.speed=0;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.bomb = bomb;
        this.world = world;
    }
    public abstract void update();

    public abstract void render(SpriteBatch batch);

    public Body getBody() {
        return body;
    }
}
