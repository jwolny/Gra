package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Player;

public abstract class PlayerView {
    private Player player;
    private SpriteBatch batch;
    private Texture playerImage;
    public PlayerView(Player player) {
        this.player = player;
    }

    public void setImage(Texture image) {
        this.playerImage = image;
    }

    public void setImage(String nameOfImage)
    {
        this.playerImage=new Texture(Gdx.files.internal(nameOfImage));
    }

    public abstract void render(SpriteBatch batch);

    void dispose() {
        playerImage.dispose();
        player.getBody().setActive(false);
    }
}
