package com.mygdx.game.controller.PlayerTools;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.controller.BombTools.BombController;
import com.mygdx.game.controller.BombTools.BombControllerInterface;
import com.mygdx.game.model.PlayerTools.Player;
import com.mygdx.game.view.PlayerTools.PlayerViewInterface;

public class PlayerController {
    private final Player player;
    private final PlayerViewInterface view;
    private final int up, down, left, right, putBomb;
    public PlayerController(Player player, PlayerViewInterface playerView, int up, int down, int left, int right, int putBomb){
        this.player=player;
        this.view=playerView;
        this.up=up;
        this.down=down;
        this.left=left;
        this.right=right;
        this.putBomb=putBomb;
    }

    public void checkUserInput()
    {
        if(player.isDead())
            return;
        player.setVelX(0);
        player.setVelY(0);
        if(Gdx.input.isKeyPressed(up))
            player.setVelY(player.getSpeed());
        if(Gdx.input.isKeyPressed(down))
            player.setVelY(-player.getSpeed());
        if(Gdx.input.isKeyPressed(left))
            player.setVelX(-player.getSpeed());
        if(Gdx.input.isKeyPressed(right))
            player.setVelX(player.getSpeed());
        if(Gdx.input.isKeyJustPressed(putBomb)){
            BombControllerInterface bombController=new BombController();
            bombController.explode(player.getX(),player.getY(),player.getWorld());
        }
        player.getBody().setLinearVelocity(player.getVelX(), player.getVelY());
        player.update();
    }

    public void render(){
        view.render();
    }
}
