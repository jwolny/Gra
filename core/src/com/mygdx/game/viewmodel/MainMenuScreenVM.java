package com.mygdx.game.viewmodel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.BomberMan;
import com.mygdx.game.ChooseMapScreen;
import com.mygdx.game.view.MainMenuScreenV;

public class MainMenuScreenVM {
    private final BomberMan game;
    private final MainMenuScreenV view;
    private Music menuMusic;

    public MainMenuScreenVM(BomberMan game, MainMenuScreenV view){
        this.game = game;
        this.view = view;
    }
    public void playMenuMusic(String path){
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        menuMusic.setLooping(true);
        menuMusic.play();
    }
    public void onStartButtonClicked(){
        view.dispose();
        dispose();
        game.setScreen(new ChooseMapScreen(game));
    }
    public void onExitButtonClicked(){
        view.dispose();
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
