package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.model.MapHelper;
import com.mygdx.game.model.Player;
import com.mygdx.game.viewmodel.PlayerViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private MapHelper mapHelper;
    private Music gameMusic;

    private List<PlayerViewModel> players = new ArrayList<PlayerViewModel>();
    //napisze zaraz rendera do view tych playerow i wyrenderuje ich na ekranie


    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.mapHelper = new MapHelper(this);
        this.orthogonalTiledMapRenderer = mapHelper.setUpMap();
        this.gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Gra.mp3"));
    }

    @Override
    public void show() {
        gameMusic.setLooping(true);
        gameMusic.play();
    }

    private void cameraUpdate(){
        camera.position.set(480,480,0);
        camera.update();
    }

    private void update(){
        cameraUpdate();
        world.step(1/60f, 6, 2);
        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        for(PlayerViewModel v : players) v.update();

        // tutaj mozna dodac klikanie exit
    }

    @Override
    public void render(float delta) {
        this.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        orthogonalTiledMapRenderer.render();

        batch.begin();
        mapHelper.render();
        batch.end();
        // do rysowania obramowania objects body - dopoki nie ma spritow na postaciach
        box2DDebugRenderer.render(world, camera.combined.scl(32.0f));
    }

    @Override
    public void resize(int width, int height) {

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
        gameMusic.stop();
        gameMusic.dispose();
        batch.dispose();
    }

    public World getWorld(){
        return world;
    }

    public void setPlayer(PlayerViewModel player) {
        players.add(player);
    }
}
