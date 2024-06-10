package com.mygdx.game.controller.BombTools;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.model.BombTools.FlameModel;
import com.mygdx.game.view.BombTools.BombViewInterface;
import com.mygdx.game.view.BombTools.FlameView;
import com.mygdx.game.view.MapAndGame.GameScreen;

public class BombController implements BombControllerInterface {
    public void explode(float x, float y, World world){
        BombFactoryInterface bombFactory=new DefaultBombFlamesFactory();
        Bomb bomb=bombFactory.createBomb(x,y,world);
        BombViewInterface bombView=bombFactory.createBombView(bomb);

        bombView.update();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                FlameModel flameModel=bombFactory.createFlameModel(bomb);
                FlameView flameView=bombFactory.createFlameView(flameModel);
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
