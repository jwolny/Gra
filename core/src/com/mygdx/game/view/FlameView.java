package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.FlameModel;

import static com.mygdx.game.view.Textures.*;

public class FlameView {
    private Texture texture;
    private SpriteBatch batch;

    public FlameView(SpriteBatch batch) {
        texture = FLAME_T.getTexture();
        this.batch = batch;
    }
    public void render( FlameModel flameModel) {
        float size = flameModel.getBody().getFixtureList().get(0).getShape().getRadius() * 2;
        float posX = flameModel.getBody().getPosition().x - size / 2;
        float posY = flameModel.getBody().getPosition().y - size / 2;
        batch.draw(texture, posX, posY, size, size);
    }
    public void dispose() {
        texture.dispose();
    }
}
