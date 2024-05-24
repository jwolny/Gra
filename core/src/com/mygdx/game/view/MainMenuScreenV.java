package com.mygdx.game.view;

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
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.ImageButtonUtils;
import com.mygdx.game.viewmodel.MainMenuScreenVM;


public class MainMenuScreenV implements Screen {
    final private MainMenuScreenVM VM;
    final BomberMan game;
    final private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    final private Texture startButtonUp;
    final private Texture exitButtonUp;
    final private Texture startButtonDown;
    final private Texture exitButtonDown;
    private Table mainTable;
    public MainMenuScreenV(final BomberMan game){
        this.game = game;
        VM = new MainMenuScreenVM(game,this);
        batch = new SpriteBatch();
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false,960,960);

        startButtonUp = new Texture(Gdx.files.internal("Buttons/start_button.png"));
        exitButtonUp = new Texture(Gdx.files.internal("Buttons/exit_button.png"));
        startButtonDown = new Texture(Gdx.files.internal("Buttons/start_down.png"));
        exitButtonDown = new Texture(Gdx.files.internal("Buttons/exit_down.png"));
    }
    public void show(){
        VM.playMenuMusic("Music/main_menu_music.wav");
        viewport = new ExtendViewport(Constants.width,Constants.height);
        stage = new Stage(viewport);
        Actor actor = new Image(new Texture(Gdx.files.internal("logo.png")));
        actor.setSize(300,300);
        actor.setPosition(50, Constants.height/2 + 180);
        stage.addActor(actor);

        mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);
        ImageButtonUtils.addButton(startButtonUp,startButtonDown,mainTable,600,296,40).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onStartButtonClicked();
            }
        });
        ImageButtonUtils.addButton(exitButtonUp,exitButtonDown,mainTable,600,296,40).addListener(new ClickListener(){
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
        batch.dispose();
        mainTable.clear();
        stage.dispose();
    }

}
