package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.model.BombTools.BodyBomb;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.model.BombTools.FlameModel;
import com.mygdx.game.model.PlayerTools.Player;
import com.mygdx.game.view.BombTools.BombView;
import com.mygdx.game.view.BombTools.FlameView;
import com.mygdx.game.view.MapAndGame.GameScreen;
import com.mygdx.game.view.PlayerTools.PlayerView;

import static com.mygdx.game.others.Constants.PPM;

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
            Body bodyBomb= BodyBomb.createBody(player.getX(), player.getY(), 15/PPM, player.getWorld());
            Bomb bomb=new Bomb(bodyBomb, player.getX(), player.getY(), 15/PPM, player.getWorld());
            BombView bombView=new BombView(bomb);
            bombView.update();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    FlameModel flameModel = new FlameModel(bomb.getRadius(), bomb.getX(), bomb.getY(), bomb.getWorld());
                    FlameView flameView = new FlameView(flameModel);
                    GameScreen.flames.add(flameView);
                    bombView.dispose();
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            flameModel.dispose();
                            GameScreen.flames.remove(flameView);
                        }
                    }, 0.1f);
                }
            }, 1f);
        }
        player.getBody().setLinearVelocity(player.getVelX(), player.getVelY());
        player.update();
    }

    public void render(){
        view.render();
    }

}
