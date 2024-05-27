package com.mygdx.game.viewmodel.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.BomberMan;
import com.mygdx.game.GameScreen;
import com.mygdx.game.view.MenuScreens.ChooseMapScreenV;
import com.mygdx.game.view.MenuScreens.MainMenuScreenV;

public class ChooseMapScreenVM {
    private final BomberMan game;
    private final ChooseMapScreenV view;
    private Music menuMusic;
    private int playerCount = 0;
    private int botCount = 0;
    private String map = null;

    public ChooseMapScreenVM(BomberMan game, ChooseMapScreenV view){
        this.game = game;
        this.view = view;
    }
    public void playMenuMusic(String path){
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        menuMusic.setLooping(true);
        menuMusic.play();
    }
    public void onMapButtonClicked(String s){
        view.GoButton.setDisabled(goDisabled());
    }
    public void onExitButtonClicked(){
        view.dispose();
        dispose();
        game.setScreen(new MainMenuScreenV(game));
    }
    public void onPlayerButtonClicked(int p, ImageButton b){
        if(b.isDisabled()) return;
        playerCount = p;
        view.ThreeButtonB.setDisabled(disableNumber(3));
        view.ZeroButtonB.setDisabled(disableZero());
        view.GoButton.setDisabled(goDisabled());
    }
    public void onBotButtonClicked(int p, ImageButton b){
        if(b.isDisabled()) return;
        botCount = p;
        view.ThreeButtonB.setDisabled(disableNumber(3));
        view.ZeroButtonB.setDisabled(disableZero());
        view.GoButton.setDisabled(goDisabled());
    }
    public void onGoButtonClicked(ImageButton b){
        if(b.isDisabled()) return;
        view.dispose();
        dispose();
        game.setScreen(new GameScreen(view.camera));
    }
    public void dispose(){
        if(menuMusic != null) {
            menuMusic.stop();
            menuMusic.dispose();
        }
    }
    public boolean disableNumber(int x){
        return playerCount + x > 4;
    }
    public boolean disableZero(){
        return playerCount == 1;
    }
    public boolean goDisabled(){
        return (playerCount+botCount < 2 && map == null);
    }
}