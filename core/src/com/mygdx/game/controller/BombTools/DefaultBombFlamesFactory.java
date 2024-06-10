package com.mygdx.game.controller.BombTools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.BombTools.BodyBomb;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.model.BombTools.FlameModel;
import com.mygdx.game.view.BombTools.BombView;
import com.mygdx.game.view.BombTools.BombViewInterface;
import com.mygdx.game.view.BombTools.FlameView;

import static com.mygdx.game.others.Constants.PPM;

public class DefaultBombFlamesFactory implements BombFlamesFactoryInterface {
    public Bomb createBomb(float x, float y, World world){
        Body bodyBomb= BodyBomb.createBody(x, y, 15/PPM, world);
        return new Bomb(bodyBomb, x, y, 15/PPM, world);
    }

    public BombViewInterface createBombView(Bomb bomb){
        return new BombView(bomb);
    }

    public FlameModel createFlameModel(Bomb bomb, float radius){
        return new FlameModel(radius, bomb.getX(), bomb.getY(), bomb.getWorld());
    }

    public FlameView createFlameView(FlameModel model){
        return new FlameView(model);
    }
}
