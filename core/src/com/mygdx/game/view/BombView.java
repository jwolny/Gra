package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.viewmodel.BombTools.BombListener;
import com.mygdx.game.viewmodel.BombTools.BombViewModel;

import static com.mygdx.game.view.Textures.BOMB_T;

public class BombView implements BombListener {
    private BombViewModel viewModel;
    private Sound bombSound;
    private Texture bombImage;

    public BombView(BombViewModel viewModel, String nameOfSound) {
        this.viewModel = viewModel;
        bombSound = Gdx.audio.newSound(Gdx.files.internal(nameOfSound));
        bombImage = BOMB_T.getTexture();
    }

    public void setTexture(String nameOfTexture){
        this.bombImage=new Texture(Gdx.files.internal(nameOfTexture));
    }

    public void setSound(String nameOfSound) {
        bombSound = Gdx.audio.newSound(Gdx.files.internal(nameOfSound));
    }

    public void render(SpriteBatch batch) {
    }

    public void dispose() {
        bombSound.play();
        viewModel.getBody().setActive(false);
        viewModel.getWorld().destroyBody(viewModel.getBody());
        //bombImage.dispose();
    }
}
