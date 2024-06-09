package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.MapTools.GenerateMap;
import com.mygdx.game.model.MapTools.MapHelper;
import com.mygdx.game.model.WorldContactLis;
import com.mygdx.game.view.MapDrawer;
import com.mygdx.game.view.MenuScreens.EndingScreenV;
import com.mygdx.game.viewmodel.PlayerTools.PlayerViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenAdapter {
    public OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private GenerateMap generateMap;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Music gameMusic;
    final BomberMan game;
    private MapDrawer mapDrawer;

    private List<PlayerViewModel> players = new ArrayList<PlayerViewModel>();
    //napisze zaraz rendera do view tych playerow i wyrenderuje ich na ekranie


    public GameScreen(OrthographicCamera camera, final BomberMan game) {
        this.game = game;
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.generateMap = new GenerateMap(this);
        this.world = generateMap.getWorld();
        this.mapDrawer = new MapDrawer(generateMap);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        //this.mapHelper = new MapHelper(this);
        //this.orthogonalTiledMapRenderer = mapHelper.setUpMap();
        this.gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Gra.mp3"));
        world.setContactListener(new WorldContactLis());
    }

    @Override
    public void show() {
        gameMusic.setLooping(true);
        gameMusic.play();
    }

    private void cameraUpdate(){
        camera.position.set(960/2,960/2,0);
        camera.update();
    }

    private void update(){
        cameraUpdate();
        world.step(1/60f, 6, 2);
        batch.setProjectionMatrix(camera.combined);
        //orthogonalTiledMapRenderer.setView(camera);

        generateMap.getOrthogonalTiledMapRenderer().setView(camera);
        players = generateMap.getPlayers();

        int temp = 0;
        for(PlayerViewModel v : players) {
            v.update();
            if(!v.getPlayer().isDead()) ++temp;
        }
        if(temp <= 1){
            this.game.setScreen(new EndingScreenV(this.game,1));
        }
    }

    @Override
    public void render(float delta) {
        this.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //orthogonalTiledMapRenderer.render();
        batch.begin();
        //mapHelper.render();
        mapDrawer.render();
        batch.end();

        for(PlayerViewModel viewModel : players)
            viewModel.render(batch);
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
