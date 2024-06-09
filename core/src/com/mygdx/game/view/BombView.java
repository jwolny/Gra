package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.BombTools.Bomb;

import static com.mygdx.game.others.Constants.PPM;
import static com.mygdx.game.others.Textures.BOMB_T;

public class BombView implements BombObserver {
    private Bomb bomb;
    private Sound bombSound;
    private Texture bombImage;
    private Sprite sprite;

    public BombView(Bomb bomb) {
        this.bomb=bomb;
        bombSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bomboclat.mp3"));
        bombImage = BOMB_T.getTexture();
        GameScreen.bombs.add(this);
    }

    public void setTexture(String nameOfTexture){
        this.bombImage=new Texture(Gdx.files.internal(nameOfTexture));
    }

    public void setSound(String nameOfSound) {
        bombSound = Gdx.audio.newSound(Gdx.files.internal(nameOfSound));
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
