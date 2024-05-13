package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;
import jdk.incubator.vector.VectorOperators;

public class ChooseMapScreen implements Screen {

    final BomberMan game;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Table chooseTable;
    private Music menuMusic;

    public ChooseMapScreen(final BomberMan game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,960,960);
    }

    @Override
    public void show() {
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/main_menu_music.wav"));
        menuMusic.setLooping(true);
        menuMusic.play();

        viewport = new ExtendViewport(Constants.width,Constants.height);
        stage = new Stage(viewport);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.font;
        labelStyle.font.getData().setScale(4);
        Label label = new Label("Wybierz mape", labelStyle);
        label.setPosition(Constants.width/2 - 100, Constants.height/2 + 350);

        chooseTable = new Table();
        chooseTable.setFillParent(true);
        stage.addActor(chooseTable);
        stage.addActor(label);
        Texture c = new Texture(Gdx.files.internal("Buttons/plansza.png"));
        ImageButtonUtils.addButton(c,c,chooseTable,200,200,40).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                game.setScreen(new GameScreen(camera));
            }
        });
        Gdx.input.setInputProcessor(stage);
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
        menuMusic.stop();
        menuMusic.dispose();
        chooseTable.clear();
        stage.dispose();
    }
}
