package com.mygdx.game.controller.PlayerTools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.PlayerTools.BodyPlayer;
import com.mygdx.game.model.PlayerTools.Player;
import com.mygdx.game.view.PlayerTools.PlayerView;
import com.mygdx.game.view.PlayerTools.PlayerViewInterface;

import static com.mygdx.game.others.Constants.PPM;

public class DefaultPlayerFactory implements PlayerFactoryInterface {
    public Player createPlayer(float x, float y, World world) {
        Body body = BodyPlayer.createBody(
                x,
                y,
                PPM - 3,
                PPM - 3,
                world);
        return new Player(PPM-3,PPM-3, body, world, 100.0f);
    }

    public PlayerViewInterface createPlayerView(Player player){
        PlayerViewInterface playerViewInterface=new PlayerView(player);
        player.setPlayerObserver(playerViewInterface);
        return playerViewInterface;
    }

    public PlayerController createPlayerController(Player player, PlayerViewInterface playerView, int up, int down, int left, int right, int putBomb){
        return new PlayerController(player,playerView,up,down,left,right,putBomb);
    }
}
