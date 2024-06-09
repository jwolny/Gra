package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.PlayerTools.Player;
import com.mygdx.game.view.PlayerView;

public class PlayerController {
    private final Player player;
    private final PlayerView view;
    public PlayerController(Player player, PlayerView playerView){
        this.player=player;
        this.view=playerView;
    }

    public void checkUserInput()
    {
        if(player.isDead())
            return;
        player.setVelX(0);
        player.setVelY(0);
        if(Gdx.input.isKeyPressed(player.getUp()))
            player.setVelY(player.getSpeed());
        if(Gdx.input.isKeyPressed(player.getDown()))
            player.setVelY(-player.getSpeed());
        if(Gdx.input.isKeyPressed(player.getLeft()))
            player.setVelX(-player.getSpeed());
        if(Gdx.input.isKeyPressed(player.getRight()))
            player.setVelX(player.getSpeed());
        if(Gdx.input.isKeyJustPressed(player.getBomb())){
            player.dropBomb();
        }
        player.getBody().setLinearVelocity(player.getVelX(), player.getVelY());
        player.update();
    }

    public void render(){
        view.render();
    }

}
