package com.mygdx.game.model.PlayerTools;

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
        this.velX = 0;
        this.velY = 0;
        this.speed = 3; // podmiana w ruchu PlayerViewModel
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.bomb = bomb;
        this.world = world;
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

    public int getUp(){
        return up;
    }

    public int getDown(){
        return down;
    }

    public int getLeft(){
        return left;
    }

    public int getRight(){
        return right;
    }

    public int getBomb(){
        return bomb;
    }

    public World getWorld() {
        return world;
    }
}
