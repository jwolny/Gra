package com.mygdx.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.viewmodel.PlayerListener;
import com.mygdx.game.viewmodel.PlayerViewModel;

import java.util.ArrayList;

import static com.mygdx.game.model.Constants.PPM;

public class Player extends PlayerEntity {
    private PlayerListener playerListener;
    public float hitPoints;
    public boolean dead;
    public Player(float width, float height, Body body, int up, int down, int left, int right, int bomb, World world, float hitPoints)
    {
        super(width, height, body, up, down, left, right, bomb, world);
        dead=false;
        this.hitPoints=hitPoints;
    }

    public float getHP(){
        return hitPoints;
    }

    public void modifyHP(float x){
        hitPoints+=x;
    }

    public void setDead(){
        dead=true;
    }

    public boolean isDead(){
        return dead;
    }
}
