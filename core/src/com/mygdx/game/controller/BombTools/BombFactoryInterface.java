package com.mygdx.game.controller.BombTools;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.view.BombTools.BombViewInterface;

public interface BombFactoryInterface {
    Bomb createBomb(float x, float y, World world);
    BombViewInterface createBombView(Bomb bomb);
}
