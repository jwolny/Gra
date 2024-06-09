package com.mygdx.game.model.BombTools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.view.GameScreen;
import com.mygdx.game.model.FlameModel;
import com.mygdx.game.view.BombObserver;
import com.mygdx.game.view.FlameView;

public class Bomb extends BombHelper {
    private BombObserver bombObserver;
    public Bomb(Body body, float x, float y, float radius, World world) {
        super(body, x, y, radius, world);
    }

    public void setBombObserver(BombObserver bombObserver){
        this.bombObserver=bombObserver;
    }

    public void explode(){
        bombObserver.update();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                FlameModel flameModel = new FlameModel(radius, x, y, world);
                FlameView flameView = new FlameView(flameModel);
                GameScreen.flames.add(flameView);
                bombObserver.dispose();
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
