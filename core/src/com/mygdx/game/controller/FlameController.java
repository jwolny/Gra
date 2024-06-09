package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameScreen;
import com.mygdx.game.model.FlameModel;
import com.mygdx.game.view.FlameView;

public class FlameController {
    private FlameModel flameModel;
    private FlameView flameView;

    public FlameController(float size, float posX, float posY, World world) {
        flameModel = new FlameModel(size, posX, posY, world);
        flameView = new FlameView(GameScreen.batch);
    }

    public void render() {
        flameView.render(flameModel);
    }

    public void dispose() {
        flameModel.dispose();
        flameView.dispose();
    }

    public FlameModel getModel() {
        return flameModel;
    }
}
