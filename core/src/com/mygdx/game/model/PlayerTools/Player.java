package com.mygdx.game.model.PlayerTools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.view.PlayerTools.PlayerObserver;

import java.util.ArrayList;

import static com.mygdx.game.others.Constants.PPM;

public class Player extends PlayerEntity {
    private float hitPoints;
    private boolean dead;
    private PlayerObserver playerObserver;
    private final static ArrayList<Player> playerList=new ArrayList<>();
    public Player(float width, float height, Body body, World world, float hitPoints)
    {
        super(width, height, body, world);
        dead=false;
        this.hitPoints=hitPoints;
        for(Fixture v : body.getFixtureList()) v.setUserData(this);
        playerList.add(this);
    }

    public float getHP(){
        return hitPoints;
    }

    public void modifyHP(float x){
        hitPoints+=x;
    }

    // zmiana procentowa!
    public void modifySpeed(float x) { speed *= x;}

    public boolean isDead(){
        return dead;
    }

    public void incrementRadius() {radius += 1;}

    public void update(){
        x=body.getPosition().x * PPM;
        y=body.getPosition().y * PPM;
        if(hitPoints<=0) {
            dead = true;
            playerObserver.update();
        }
    }

    public void setPlayerObserver(PlayerObserver playerObserver) {
        this.playerObserver = playerObserver;
    }

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }
}
