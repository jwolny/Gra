package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Player;
import com.mygdx.game.viewmodel.PlayerListener;
import com.mygdx.game.viewmodel.PlayerViewModel;

public class PlayerView implements PlayerListener {
    private PlayerViewModel viewModel;
    private SpriteBatch batch;
    private Texture playerImage;
    public PlayerView(PlayerViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.setPlayerListener(this);
    }

    public void setImage(String nameOfImage)
    {
        this.playerImage=new Texture(Gdx.files.internal(nameOfImage));
    }

    public void render(){

    }

    public void dispose() {
        //playerImage.dispose();
    }
    public SpriteBatch getBatch(){
        return batch;
    }
}
