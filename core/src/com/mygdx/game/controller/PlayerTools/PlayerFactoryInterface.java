package com.mygdx.game.controller.PlayerTools;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.PlayerTools.Player;
import com.mygdx.game.view.PlayerTools.PlayerViewInterface;

public interface PlayerFactoryInterface {
    Player createPlayer(float x, float y, World world);
    PlayerViewInterface createPlayerView(Player player);
    PlayerController createPlayerController(
            Player player, PlayerViewInterface playerView, int up, int down, int left, int right, int putBomb);
}
