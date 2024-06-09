package com.mygdx.game.view.PlayerTools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.PlayerTools.Player;
import com.mygdx.game.view.MapAndGame.GameScreen;

import static com.mygdx.game.others.Constants.PPM;

public class PlayerView implements PlayerObserver {
    private final Player player;
    private Texture playerImage;
    private TextureRegion textureRegion;
    private Sprite sprite;
    public PlayerView(Player player) {
        this.player = player;
        playerImage = new Texture(Gdx.files.internal("Pixel Crawler - FREE - 1.8/Heroes/Knight/Idle/Idle-Sheet.png"));
        textureRegion = new TextureRegion(playerImage, 0,0,32, 32);
        sprite=new Sprite(textureRegion);
        player.getBody().setUserData(sprite);
    }

    public void setImage(String nameOfImage)
    {
        this.playerImage=new Texture(Gdx.files.internal(nameOfImage));
    }

    public void render(){
        if(sprite==null)
            return;
        sprite.setPosition(player.getPosition().x*PPM-player.getWidth()/2, player.getPosition().y*PPM-player.getHeight()/2);
        GameScreen.getBatch().begin();
        sprite.draw(GameScreen.getBatch());
        GameScreen.getBatch().end();
    }

    public void dispose() {
        //playerImage=new Texture(Gdx.files.internal("Pixel Crawler - FREE - 1.8/Heroes/Knight/Death/Death-Sheet.png"));
        //textureRegion=new TextureRegion(playerImage, 230,0,32, 32);
        //sprite=new Sprite(textureRegion);
        sprite=null;
    }
}
