package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.BomberMan;
import com.mygdx.game.view.MenuScreens.ChooseMapScreenV;
import com.mygdx.game.others.Textures;

public class MainMenuScreenC {
    private final BomberMan game;
    public MainMenuScreenC(BomberMan game){
        this.game = game;
    }
    public void playMenuMusic(Music music){
        music.setLooping(true);
        music.play();
    }
    public void onStartButtonClicked(){
        game.getScreen().dispose();
        game.setScreen(new ChooseMapScreenV(game));
    }
    public void onExitButtonClicked(){
        game.getScreen().dispose();
        Textures.disposeAll();
        Gdx.app.exit();
        System.exit(-1);
    }


}
