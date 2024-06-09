package com.mygdx.game.controller;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.model.BombTools.FlameModel;
import com.mygdx.game.view.BombTools.BombView;
import com.mygdx.game.view.BombTools.FlameView;
import com.mygdx.game.view.MapAndGame.GameScreen;

public class BombController {
    public static void explode(float x, float y, World world){
        Bomb bomb=DefaultBombFlamesFactory.createBomb(x,y,world);
        BombView bombView=DefaultBombFlamesFactory.createBombView(bomb);

        bombView.update();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                FlameModel flameModel=DefaultBombFlamesFactory.createFlameModel(bomb);
                FlameView flameView=DefaultBombFlamesFactory.createFlameView(flameModel);
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
}
