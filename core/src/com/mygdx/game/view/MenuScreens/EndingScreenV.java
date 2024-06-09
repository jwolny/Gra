package com.mygdx.game.view.MenuScreens;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.mygdx.game.controller.MainMenuScreenC;


public class EndingScreenV implements Screen {
    private Music music = Gdx.audio.newMusic(Gdx.files.internal("Music/main_menu_music.wav"));
    final private MainMenuScreenC VM;
    final BomberMan game;
    final private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Table mainTable;
    private int winner;
    public EndingScreenV(final BomberMan game, int winner){
        this.winner = winner;
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
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.font;
        labelStyle.font.getData().setScale(3);
        Label label = new Label("Koniec", labelStyle);
        label.setPosition(Constants.width/2,Constants.height/2 + 300);
        stage.addActor(label);

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
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
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
