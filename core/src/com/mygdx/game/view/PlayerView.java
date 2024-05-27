package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.viewmodel.PlayerTools.PlayerListener;
import com.mygdx.game.viewmodel.PlayerTools.PlayerViewModel;

import static com.mygdx.game.model.Constants.PPM;

public class PlayerView implements PlayerListener {
    private PlayerViewModel viewModel;
    private Texture playerImage;
    private TextureRegion textureRegion;
    private Sprite sprite;
    public PlayerView(PlayerViewModel viewModel) {
        this.viewModel = viewModel;
        playerImage=new Texture(Gdx.files.internal("Pixel Crawler - FREE - 1.8/Heroes/Knight/Idle/Idle-Sheet.png"));
        textureRegion=new TextureRegion(playerImage, 0,0,32, 32);
        sprite=new Sprite(textureRegion);
        viewModel.getBody().setUserData(sprite);
    }

    public void setImage(String nameOfImage)
    {
        this.playerImage=new Texture(Gdx.files.internal(nameOfImage));
    }

    public void render(SpriteBatch batch){
        if(sprite==null)
            return;
        sprite.setPosition(viewModel.getPosition().x*PPM-viewModel.getWidth()/2, viewModel.getPosition().y*PPM-viewModel.getHeight()/2);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void dispose() {
        //playerImage=new Texture(Gdx.files.internal("Pixel Crawler - FREE - 1.8/Heroes/Knight/Death/Death-Sheet.png"));
        //textureRegion=new TextureRegion(playerImage, 230,0,32, 32);
        //sprite=new Sprite(textureRegion);
        sprite=null;
        viewModel.getBody().setActive(false);
    }
}
