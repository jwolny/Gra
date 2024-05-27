package com.mygdx.game.viewmodel.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.BomberMan;
import com.mygdx.game.view.MenuScreens.ChooseMapScreenV;
import com.mygdx.game.view.MenuScreens.MainMenuScreenV;

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
        game.setScreen(new ChooseMapScreenV(game));
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
