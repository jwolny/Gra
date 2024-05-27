package com.mygdx.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.viewmodel.PlayerViewModel;

import java.util.ArrayList;

import static com.mygdx.game.model.Constants.PPM;

public class Player extends PlayerEntity {
    public static ArrayList<Player> playerList=new ArrayList();
    public float hitPoints;
    public boolean dead;
    public Player(float width, float height, Body body, int up, int down, int left, int right, int bomb, World world, float hitPoints)
    {
        super(width, height, body, up, down, left, right, bomb, world);
        dead=false;
        this.hitPoints=hitPoints;
        playerList.add(this);
    }

    @Override
    public void update()
    {
        x=body.getPosition().x * PPM;
        y=body.getPosition().y * PPM;
    }

    public boolean inRange(float x, float y, float radius) //sprawdzamy czy jest w rangu bomby
    {
        if(((this.x-x)*(this.x-x))+((this.y-y)*(this.y-y))<radius*radius*PPM*PPM)
            return true;
        return false;
    }

    PlayerEntity getPlayerEntity(){
        return this;
    }

    public void modifyHP(float x)
    {
        hitPoints+=x;
        if(hitPoints<=0)
            dead=true;
    }
}
