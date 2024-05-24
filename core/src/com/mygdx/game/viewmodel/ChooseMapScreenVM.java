package com.mygdx.game.viewmodel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.BomberMan;
import com.mygdx.game.GameScreen;
import com.mygdx.game.view.ChooseMapScreenV;
import com.mygdx.game.view.MainMenuScreenV;

public class ChooseMapScreenVM {
    private final BomberMan game;
    private final ChooseMapScreenV view;
    private Music menuMusic;

    public ChooseMapScreenVM(BomberMan game, ChooseMapScreenV view){
        this.game = game;
        this.view = view;
    }
    public void playMenuMusic(String path){
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        menuMusic.setLooping(true);
        menuMusic.play();
    }
    public void onMapButtonClicked(){
        view.dispose();
        dispose();
        game.setScreen(new GameScreen(view.camera));
    }
    public void onExitButtonClicked(){
        view.dispose();
        dispose();
        game.setScreen(new MainMenuScreenV(game));
    }
    public void dispose(){
        if(menuMusic != null) {
            menuMusic.stop();
            menuMusic.dispose();
        }
    }
}
