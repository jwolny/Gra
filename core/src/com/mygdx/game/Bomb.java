package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;

import static com.mygdx.game.Player.playerList;

public class Bomb extends BombHelper {
    public Bomb(Body body, float x, float y, float radius, World world) {
        super(body, x, y, radius, world);
        timer = new Timer.Task() {
            @Override
            public void run() {
                this.cancel();
                for (Player player : playerList)
                    if (!player.dead && player.inRange(x, y, radius))
                        player.loseHP(25);
                dispose();
            }
        };
        Timer.schedule(timer, 1, 0f);
    }


    @Override
    public void update() {

    }

    @Override
    public void draw() {

    }

    public void render(SpriteBatch batch) {

    }

    private void dispose() {
        bombSound.play();
        body.setActive(false);
    }
}
