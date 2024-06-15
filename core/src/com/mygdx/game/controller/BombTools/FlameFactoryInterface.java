package com.mygdx.game.controller.BombTools;

import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.model.BombTools.FlameModel;
import com.mygdx.game.view.BombTools.FlameView;

public interface FlameFactoryInterface {
    FlameModel createFlameModel(Bomb bomb, float radius);
    FlameView createFlameView(FlameModel flameModel);
}
