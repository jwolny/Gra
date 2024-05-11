package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class StartButton extends Actor{
    private TextureRegion buttonRegion;

    StartButton(TextureRegion r){
        this.buttonRegion = r;
        setBounds(320,500,400,150);
    }
    @Override
    public void draw(Batch batch,float l){
        batch.draw(buttonRegion, getX(), getY(), getWidth(), getHeight());
    }
    public void act(float d){
        //if(Gdx.)
    }
}
