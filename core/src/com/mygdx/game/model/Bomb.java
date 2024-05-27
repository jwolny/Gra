package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.viewmodel.BombListener;

import static com.mygdx.game.model.Constants.PPM;
import static com.mygdx.game.model.Player.playerList;

public class Bomb extends BombHelper {
    private BombListener bombListener;
    public Bomb(Body body, float x, float y, float radius, World world) {
        super(body, x, y, radius, world);
    }

    public void explode(){
        timer = new Timer.Task() {
            @Override
            public void run() {
                timer.cancel();
                for (Player player : playerList) {
                    if (!player.dead && player.inRange(x, y, radius)) {
                        player.modifyHP(-25.0f);
                    }
                }
                dispose();
            }
        };
        Timer.schedule(timer, 1, 1f);
    }


    @Override
    public void update() {

    }

    private void dispose() {
        bombListener.dispose();
    }

    public void setBombListener(BombListener bombListener){
        this.bombListener=bombListener;
    }
}
