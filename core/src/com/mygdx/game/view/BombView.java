package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.viewmodel.BombListener;
import com.mygdx.game.viewmodel.BombViewModel;

public class BombView implements BombListener {
    private BombViewModel viewModel;
    private Sound bombSound;
    private Texture bombImage;

    public BombView(BombViewModel viewModel, String nameOfSound) {
        this.viewModel = viewModel;
        bombSound = Gdx.audio.newSound(Gdx.files.internal(nameOfSound));
        viewModel.setBombListener(this);
    }

    public void setTexture(String nameOfTexture){
        this.bombImage=new Texture(Gdx.files.internal(nameOfTexture));
    }

    public void setSound(String nameOfSound) {
        bombSound = Gdx.audio.newSound(Gdx.files.internal(nameOfSound));
    }

    public void render(SpriteBatch batch) {
        //viewModel.render(batch);
    }

    public void dispose() {
        bombSound.play();
        viewModel.getBody().setActive(false);
        //bombImage.dispose();
    }
}
