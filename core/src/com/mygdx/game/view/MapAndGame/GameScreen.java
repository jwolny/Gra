package com.mygdx.game.view.MapAndGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.BomberMan;
import com.mygdx.game.controller.PlayerTools.PlayerController;
import com.mygdx.game.controller.WorldContactLis;
import com.mygdx.game.model.MapTools.GenerateMap;
import com.mygdx.game.model.PlayerTools.Player;
import com.mygdx.game.view.BombTools.BombView;
import com.mygdx.game.view.BombTools.FlameView;
import com.mygdx.game.view.MenuScreens.EndingScreenV;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;


import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenAdapter {
    public OrthographicCamera camera;
    private static SpriteBatch batch;

    private final World world;
    private final GenerateMap generateMap;
    private final Music gameMusic;
    final BomberMan game;
    private final MapDrawer mapDrawer;
    public int temp;
    private Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();

    public static List<FlameView> flames = new ArrayList<>();
    public static List<BombView> bombs=new ArrayList<>();

    private List<PlayerController> players = new ArrayList<>();


    public GameScreen(OrthographicCamera camera, final BomberMan game) {
        this.game = game;
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.generateMap = new GenerateMap(this);
        this.world = generateMap.getWorld();
        this.mapDrawer = new MapDrawer(generateMap);
        this.gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Gra.mp3"));
        world.setContactListener(new WorldContactLis());
    }

    @Override
    public void show() {
        gameMusic.setLooping(true);
        gameMusic.play();
    }

    private void cameraUpdate(){
        camera.position.set(960/2f,960/2f,0);
        camera.update();
    }

    private void update(){
        cameraUpdate();
        world.step(1/60f, 6, 2);
        batch.setProjectionMatrix(camera.combined);
        //orthogonalTiledMapRenderer.setView(camera);

        generateMap.getOrthogonalTiledMapRenderer().setView(camera);
        players = generateMap.getPlayers();

        for(PlayerController playerController : players) {
            playerController.checkUserInput();
        }
        temp = 0;

        for(Player player: Player.getPlayerList())
            if(!player.isDead())
                ++temp;

        if(temp <= 1){
            this.game.setScreen(new EndingScreenV(this.game,1));
            dispose();
        }
    }

    @Override
    public void render(float delta) {
        this.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        mapDrawer.render();
        for(FlameView f : flames){
            f.render();
        }
        batch.end();

        for(PlayerController playerController: players)
            playerController.render();

        for(BombView bombView: bombs)
            bombView.render();
        //box2DDebugRenderer.render(world, camera.combined.scl(32.0f));
    }

    @Override
    public void dispose() {
        Player.getPlayerList().clear();
        players.clear();
        flames.clear();
        gameMusic.stop();
        gameMusic.dispose();
        batch.dispose();
    }

    public World getWorld(){
        return world;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }
}
