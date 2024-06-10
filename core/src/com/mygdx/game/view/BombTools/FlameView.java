package com.mygdx.game.view.BombTools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.BombTools.FlameModel;
import com.mygdx.game.view.MapAndGame.GameScreen;

import static com.mygdx.game.others.Constants.PPM;
import static com.mygdx.game.others.Textures.FLAME_T;

public class FlameView {
    private final FlameModel FM;
    private final Texture texture;

    private final SpriteBatch batch = GameScreen.getBatch();

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
    
}
