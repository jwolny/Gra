package com.mygdx.game.view.BombTools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.view.MapAndGame.GameScreen;

import static com.mygdx.game.others.Constants.PPM;
import static com.mygdx.game.others.Textures.BOMB_T;

public class BombView implements BombViewInterface{
    private final Bomb bomb;
    private final Sound bombSound;
    private final Texture bombImage;
    private Sprite sprite;

    public BombView(Bomb bomb) {
        this.bomb=bomb;
        bombSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bomboclat.mp3"));
        bombImage = BOMB_T.getTexture();
        GameScreen.bombs.add(this);
    }

    public void render() {
        if(sprite==null)
            return;
        sprite.setPosition(bomb.getPosition().x*PPM-4.6f*PPM, bomb.getPosition().y*PPM-4.6f*PPM);
        GameScreen.getBatch().begin();
        sprite.draw(GameScreen.getBatch());
        GameScreen.getBatch().end();
    }

    public void dispose() {
        bombSound.play();
        sprite=null;
    }
    public void update(){
        sprite=new Sprite(bombImage);
        sprite.setScale(0.1f);
        bomb.getBody().setUserData(sprite);
    }
}
