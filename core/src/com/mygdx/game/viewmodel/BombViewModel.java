package com.mygdx.game.viewmodel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.model.Bomb;

public class BombViewModel implements BombListener{
    private Bomb bomb;
    private BombListener bombListener;;

    public BombViewModel(Bomb bomb){
        this.bomb=bomb;
        bomb.setBombListener(this);
    }

    public void render(SpriteBatch batch){

    }

    public void setBombListener(BombListener bombListener){
        this.bombListener=bombListener;
    }

    public void dispose(){
        if(bombListener!=null)
            bombListener.dispose();
    }

    public void explode(){
        bomb.explode();
    }

    public Body getBody(){
        return bomb.getBody();
    }
}
