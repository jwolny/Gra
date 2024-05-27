package com.mygdx.game.viewmodel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.model.BodyBomb;
import com.mygdx.game.model.Bomb;
import com.mygdx.game.model.Player;
import com.mygdx.game.view.BombView;

import java.util.ArrayList;

import static com.mygdx.game.model.Constants.PPM;

public class PlayerViewModel implements PlayerListener{
    public static ArrayList<PlayerViewModel> playerList=new ArrayList();
    private final Player player;
    private PlayerListener playerListener;

    public PlayerViewModel(Player player) {
        this.player = player;
        playerList.add(this);
    }

    public void update(){
        player.setX(getBody().getPosition().x * PPM);
        player.setY(getBody().getPosition().y * PPM);
        if(!player.dead)
            checkUserInput();
    }

    public void checkUserInput()
    {
        player.setVelX(0);
        player.setVelY(0);
        if(Gdx.input.isKeyPressed(player.getUp()))
            player.setVelY(3);
        if(Gdx.input.isKeyPressed(player.getDown()))
            player.setVelY(-3);
        if(Gdx.input.isKeyPressed(player.getLeft()))
            player.setVelX(-3);
        if(Gdx.input.isKeyPressed(player.getRight()))
            player.setVelX(3);
        if(Gdx.input.isKeyJustPressed(player.getBomb()))
            dropBomb();
        player.getBody().setLinearVelocity(player.getVelX(), player.getVelY());
    }

    public void dropBomb()
    {
        Body bodyBomb= BodyBomb.createBody(player.getX(), player.getY(), 15/PPM, player.getWorld());
        Bomb bomb=new Bomb(bodyBomb, player.getX(), player.getY(),15, player.getWorld());
        BombViewModel bombViewModel=new BombViewModel(bomb);
        BombView bombView=new BombView(bombViewModel, "Sounds/bomboclat.mp3");

        bombViewModel.explode();
    }

    public boolean inRange(float x, float y, float radius) //sprawdzamy czy jest w rangu bomby
    {
        if(((player.getX()-x)*(player.getX()-x))+((player.getY()-y)*(player.getY()-y))<radius*radius)
            return true;
        return false;
    }

    public Body getBody(){
        return player.getBody();
    }


    public void modifyHP(float x)
    {
        player.modifyHP(x);
        if(player.getHP()<=0f) {
            player.setDead();
            if(playerListener!=null)
                playerListener.dispose();
        }
    }

    public Player getPlayer(){
        return player;
    }

    public void dispose(){

    }

    public void setPlayerListener(PlayerListener playerListener){
        this.playerListener=playerListener;
    }
}
