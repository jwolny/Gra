package com.mygdx.game.model.PlayerTools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.viewmodel.PlayerTools.PlayerListener;

public class Player extends PlayerEntity {
    private float hitPoints;
    private boolean dead;

    public Player(float width, float height, Body body, int up, int down, int left, int right, int bomb, World world, float hitPoints)
    {
        super(width, height, body, up, down, left, right, bomb, world);
        dead=false;
        this.hitPoints=hitPoints;
        for(Fixture v : body.getFixtureList()) v.setUserData(this);
    }

    public float getHP(){
        return hitPoints;
    }

    public void modifyHP(float x){
        hitPoints+=x;
    }

    // zmiana procentowa!
    public void modifySpeed(float x) { speed *= 1 + x;}

    public void setDead(){
        dead=true;
    }

    public boolean isDead(){
        return dead;
    }

    public float getRadius() {return radius;}
    public void incrementRadius() {radius += 1;}
}
