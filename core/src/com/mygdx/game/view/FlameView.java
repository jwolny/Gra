package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.FlameModel;

import static com.mygdx.game.model.Constants.PPM;
import static com.mygdx.game.view.Textures.FLAME_T;

public class FlameView {
    private FlameModel FM;
    private Texture texture;

    public FlameView(FlameModel FM) {
        this.FM = FM;
        texture = FLAME_T.getTexture();
    }

    public void render(SpriteBatch batch) {
        if(!FM.getDestroyed()) batch.draw(texture, FM.getPosX()/PPM, FM.getPosY()/PPM);
    }

    public void dispose() {
    }
}
