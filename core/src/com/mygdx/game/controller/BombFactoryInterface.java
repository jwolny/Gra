package com.mygdx.game.controller;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.model.BombTools.FlameModel;
import com.mygdx.game.view.BombTools.BombViewInterface;
import com.mygdx.game.view.BombTools.FlameView;

public interface BombFactoryInterface {
    Bomb createBomb(float x, float y, World world);
    BombViewInterface createBombView(Bomb bomb);
    FlameModel createFlameModel(Bomb bomb);
    FlameView createFlameView(FlameModel flameModel);
}
