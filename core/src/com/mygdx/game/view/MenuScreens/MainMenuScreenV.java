package com.mygdx.game.view.MenuScreens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.BomberMan;
import com.mygdx.game.others.Constants;
import com.mygdx.game.others.ImageButtonUtils;
import com.mygdx.game.others.ButtonsC;
import com.mygdx.game.controller.ScreenTools.MainMenuScreenC;
import com.badlogic.gdx.audio.Music;


public class MainMenuScreenV implements Screen {
    final private MainMenuScreenC VM;
    final BomberMan game;
    final private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Table mainTable;
    private final Music music = Gdx.audio.newMusic(Gdx.files.internal("Music/main_menu_music.wav"));
    public MainMenuScreenV(final BomberMan game){
        this.game = game;
        VM = new MainMenuScreenC(game);
        batch = new SpriteBatch();
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false,960,960);
        VM.playMenuMusic(music);
    }
    public void show(){
        viewport = new ExtendViewport(Constants.width,Constants.height);
        stage = new Stage(viewport);
        Actor actor = new Image(new Texture(Gdx.files.internal("logo.png")));
        actor.setSize(300,300);
        actor.setPosition(50, Constants.height/2 + 180);
        stage.addActor(actor);

        mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);
        ImageButtonUtils.addButton(ButtonsC.STARTBUTTON.createImageButton(), mainTable,40).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onStartButtonClicked();
            }
        });
        ImageButtonUtils.addButton(ButtonsC.EXITBUTTON.createImageButton(), mainTable,40).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onExitButtonClicked();
            }
        });
        Gdx.input.setInputProcessor(stage);
    }
    public void render(float d){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
    public void resize(int a,int b){
        viewport.update(a,b);
    }
    public void pause(){

    }
    public void resume(){

    }
    public void hide(){

    }
    public void dispose(){
        music.stop();
        music.dispose();
        batch.dispose();
        mainTable.clear();
        stage.dispose();
    }

}
