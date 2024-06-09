package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.BomberMan;
import com.mygdx.game.view.MenuScreens.ChooseMapScreenV;

public class MainMenuScreenC {
    private final BomberMan game;
    private Music menuMusic;

    public MainMenuScreenC(BomberMan game){
        this.game = game;
    }
    public void playMenuMusic(String path){
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        menuMusic.setLooping(true);
        menuMusic.play();
    }
    public void onStartButtonClicked(){
        game.getScreen().dispose();
        dispose();
        game.setScreen(new ChooseMapScreenV(game));
    }
    public void onExitButtonClicked(){
        game.getScreen().dispose();
        dispose();
        Gdx.app.exit();
        System.exit(-1);
    }
    public void dispose(){
        if(menuMusic != null) {
            menuMusic.stop();
            menuMusic.dispose();
        }
    }

}
