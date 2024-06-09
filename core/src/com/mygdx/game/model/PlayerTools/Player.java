package com.mygdx.game.model.PlayerTools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.BombTools.BodyBomb;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.view.BombView;
import com.mygdx.game.view.PlayerObserver;

import java.util.ArrayList;

import static com.mygdx.game.others.Constants.PPM;

public class Player extends PlayerEntity {
    private float hitPoints;
    private boolean dead;
    private PlayerObserver playerObserver;
    private static ArrayList<Player> playerList=new ArrayList<>();
    public Player(float width, float height, Body body, int up, int down, int left, int right, int bomb, World world, float hitPoints)
    {
        super(width, height, body, up, down, left, right, bomb, world);
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

    public void setDead(){
        dead=true;
    }

    public boolean isDead(){
        return dead;
    }

    public float getRadius() {return radius;}
    public void incrementRadius() {radius += 1;}

    public void update(){
        x=body.getPosition().x * PPM;
        y=body.getPosition().y * PPM;
        if(hitPoints<=0) {
            dead = true;
            playerObserver.dispose();
        }
    }

    //TODO to nie powinno być tutaj, zmienię to
    public void dropBomb(){
        Body bodyBomb= BodyBomb.createBody(x, y, 15/PPM, world);
        Bomb bomb=new Bomb(bodyBomb, x, y, radius, world);
        BombView bombView=new BombView(bomb);
        bomb.setBombObserver(bombView);

        bomb.explode();
    }

    public void setPlayerObserver(PlayerObserver playerObserver) {
        this.playerObserver = playerObserver;
    }

    public boolean inRange(float x, float y, float radius) //sprawdzamy czy jest w rangu bomby
    {
        return ((this.x - x) * (this.x - x)) + ((this.y - y) * (this.y - y)) < radius * radius;
    }

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }
}
