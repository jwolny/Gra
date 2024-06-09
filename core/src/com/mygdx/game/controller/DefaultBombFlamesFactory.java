package com.mygdx.game.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.BombTools.BodyBomb;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.model.BombTools.FlameModel;
import com.mygdx.game.view.BombTools.BombView;
import com.mygdx.game.view.BombTools.FlameView;

import static com.mygdx.game.others.Constants.PPM;

public class DefaultBombFlamesFactory {
    public static Bomb createBomb(float x, float y, World world){
        Body bodyBomb= BodyBomb.createBody(x, y, 15/PPM, world);
        Bomb bomb=new Bomb(bodyBomb, x, y, 15/PPM, world);
        return bomb;
    }

    public static BombView createBombView(Bomb bomb){
        return new BombView(bomb);
    }

    public static FlameModel createFlameModel(Bomb bomb){
        return new FlameModel(bomb.getRadius(), bomb.getX(), bomb.getY(), bomb.getWorld());
    }

    public static FlameView createFlameView(FlameModel model){
        return new FlameView(model);
    }
}
