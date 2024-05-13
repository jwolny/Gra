package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.mygdx.game.Constants.PPM;

public class Player extends PlayerEntity {
    private static ArrayList<Player> playerList=new ArrayList<>();
    private Texture playerImage;
    private float hitpoints;
    private boolean dead;
    public Player(float width, float height, Body body, int up, int down, int left, int right, int bomb)
    {
        super(width, height, body, up, down, left, right, bomb);
        dead=false;
        hitpoints=100f;
        playerList.add(this);
    }

    @Override
    public void update()
    {
        x=body.getPosition().x * PPM;
        y=body.getPosition().y * PPM;
        checkUserInput();
    }

    @Override
    public void render(SpriteBatch batch)
    {

    }

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
    public void loseHP(int x)
    {
        hitpoints-=x;
        if(hitpoints<=0)
            dead=true;
    }
    private boolean inRange(float x, float y, float radius) //sprawdzamy czy jest w rangu bomby
    {
        if(body.getPosition().x < x-radius || body.getPosition().x > x+radius)
            return false;
        if(body.getPosition().y < y-radius || body.getPosition().y > y+radius)
            return false;
        return true;
    }
    public void dropBomb()
    {
        Bomb bomb = new Bomb();
    }

    public class Bomb
    {
        private boolean exploded; //true jesli eksplodowala
        private Texture bombImage;
        private Circle bombCircle;
        public Bomb()
        {
            exploded=false;
            bombCircle=new Circle();
            bombCircle.x=x;
            bombCircle.y=y;
            bombCircle.radius=15; //zalezy od tego ile chcemy
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    exploded=true;
                    timer.cancel();
                    for(Player player: playerList)
                    {
                        if(player.inRange(bombCircle.x, bombCircle.y, bombCircle.radius))
                            player.loseHP(15);
                    }
                }
            }, 1500);
        }
    }

}
