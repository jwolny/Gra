package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.mygdx.game.Constants.PPM;

public class Player extends PlayerEntity {
    public static ArrayList<Player> playerList=new ArrayList<>();
    private Texture playerImage;
    private float hitpoints;
    public boolean dead;
    public Player(float width, float height, Body body, int up, int down, int left, int right, int bomb, World world)
    {
        super(width, height, body, up, down, left, right, bomb, world);
        dead=false;
        hitpoints=100f;
        playerList.add(this);
    }

    @Override
    public void update()
    {
        x=body.getPosition().x * PPM;
        y=body.getPosition().y * PPM;
        if(!dead)
            checkUserInput();
        if(dead)
            dispose();
    }

    @Override
    public void render(SpriteBatch batch) {}

    public void setImage(String nameOfImage)
    {
        this.playerImage=new Texture(Gdx.files.internal(nameOfImage));
    }
    public void checkUserInput()
    {
        velX=0;
        velY=0;
        if(Gdx.input.isKeyJustPressed(up))
            velY=20;
        if(Gdx.input.isKeyJustPressed(down))
            velY=-20;
        if(Gdx.input.isKeyJustPressed(left))
            velX=-20;
        if(Gdx.input.isKeyJustPressed(right))
            velX=20;
        if(Gdx.input.isKeyJustPressed(bomb))
            dropBomb();
        body.setLinearVelocity(velX, velY);
    }
    public void loseHP(float x)
    {
        hitpoints-=x;
        if(hitpoints<=0)
            dead=true;
    }
    public boolean inRange(float x, float y, float radius) //sprawdzamy czy jest w rangu bomby
    {
        if(((this.x-x)*(this.x-x))+((this.y-y)*(this.y-y))<radius*radius)
            return true;
        return false;
    }
    public void dropBomb()
    {
        Body bodyBomb=BodyBomb.createBody(x,y,15/PPM,this.world);
        Bomb bomb=new Bomb(bodyBomb,x,y,15/PPM,world);
    }

    void dispose() {
        playerImage.dispose();
        body.setActive(false);
    }
}
