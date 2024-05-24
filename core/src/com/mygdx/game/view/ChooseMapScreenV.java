package com.mygdx.game.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.BomberMan;
import com.mygdx.game.GameScreen;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.ImageButtonUtils;
import com.mygdx.game.viewmodel.ChooseMapScreenVM;

public class ChooseMapScreenV implements Screen {
    private final ChooseMapScreenVM VM;

    final BomberMan game;
    protected Stage stage;
    private Viewport viewport;
    public final OrthographicCamera camera;
    private Table chooseTable;
    public ChooseMapScreenV(final BomberMan game){
        this.game = game;
        camera = new OrthographicCamera();
        VM = new ChooseMapScreenVM(game,this);
        camera.setToOrtho(false,960,960);
    }

    @Override
    public void show() {
        VM.playMenuMusic("Music/main_menu_music.wav");

        viewport = new ExtendViewport(Constants.width,Constants.height);
        stage = new Stage(viewport);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.font;
        labelStyle.font.getData().setScale(3);
        Label label = new Label("Wybierz mape", labelStyle);
        label.setPosition(Constants.width/2 - 100, Constants.height/2 + 350);
        Label player = new Label("Liczba graczy:", labelStyle);
        player.setPosition(100, 400);
        Label bots = new Label("liczba botów: ", labelStyle);
        bots.setPosition(100,250);

        chooseTable = new Table();
        chooseTable.setFillParent(true);
        stage.addActor(chooseTable);
        stage.addActor(label);
        stage.addActor(player);
        stage.addActor(bots);
        Texture c = new Texture(Gdx.files.internal("Buttons/plansza.png"));
        ImageButtonUtils.addButton(c,c,chooseTable,200,200,40).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onMapButtonClicked();
            }
        });
        Gdx.input.setInputProcessor(stage);
        chooseTable.setPosition(-400,180);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        chooseTable.clear();
        stage.dispose();
    }
}
