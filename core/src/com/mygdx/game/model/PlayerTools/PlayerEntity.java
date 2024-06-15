package com.mygdx.game.model.PlayerTools;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class PlayerEntity {
    protected float x, y, velX, velY, speed;
    protected float width, height;
    protected World world;
    protected Body body;
    protected float radius;

    public PlayerEntity(float width, float height, Body body, World world){
        this.width = width;
        this.height = height;
        this.body = body;
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.velX = 0;
        this.velY = 0;
        this.speed = 3; // podmiana w ruchu PlayerViewModel
        this.world = world;
        this.radius = 2f; // tak bylo hardcodowane
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public float getSpeed() { return speed; }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public float getRadius() {return radius;}

    public World getWorld() {
        return world;
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }
}
