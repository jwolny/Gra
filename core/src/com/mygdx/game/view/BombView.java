package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.BombTools.Bomb;

import static com.mygdx.game.others.Textures.BOMB_T;

public class BombView implements BombObserver {
    private Bomb bomb;
    private Sound bombSound;
    private Texture bombImage;

    public BombView(Bomb bomb) {
        this.bomb=bomb;
        bombSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bomboclat.mp3"));
        bombImage = BOMB_T.getTexture();
    }

    public void setTexture(String nameOfTexture){
        this.bombImage=new Texture(Gdx.files.internal(nameOfTexture));
    }

    public void setSound(String nameOfSound) {
        bombSound = Gdx.audio.newSound(Gdx.files.internal(nameOfSound));
    }

    public void render() {
    }

    public void dispose() {
        bombSound.play();
    }
    public void update(Bomb bomb){

    }
}
