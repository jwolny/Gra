package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameScreen;
import com.mygdx.game.model.FlameModel;

import static com.mygdx.game.model.Constants.PPM;
import static com.mygdx.game.view.Textures.FLAME_T;

public class FlameView {
    private FlameModel FM;
    private Texture texture;

    private SpriteBatch batch = GameScreen.getBatch();

    public FlameView(FlameModel FM) {
        this.FM = FM;
        texture = FLAME_T.getTexture();
    }

    public void render() {
        if(!FM.getDestroyed()){

            batch.draw(texture, FM.getPosX() - (FM.getSize()/2)*PPM, FM.getPosY() - PPM/2, FM.getSize()*PPM, PPM);
            batch.draw(texture, FM.getPosX() - PPM/2, FM.getPosY() - (FM.getSize()/2)*PPM, PPM, FM.getSize()*PPM);
        }
    }

    public void dispose() {
    }
}
