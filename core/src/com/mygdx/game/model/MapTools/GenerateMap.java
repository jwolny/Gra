package com.mygdx.game.model.MapTools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.controller.PlayerTools.DefaultPlayerFactory;
import com.mygdx.game.controller.PlayerTools.PlayerFactoryInterface;
import com.mygdx.game.view.MapAndGame.GameScreen;
import com.mygdx.game.controller.PlayerTools.PlayerController;
import com.mygdx.game.model.Items.BombUpgrade;
import com.mygdx.game.model.Items.FirstAidKit;
import com.mygdx.game.model.Items.Items;
import com.mygdx.game.model.Items.SpeedPotion;
import com.mygdx.game.model.PlayerTools.Player;
import com.mygdx.game.view.PlayerTools.PlayerViewInterface;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.others.Constants.*;
import static java.lang.Math.random;

public class GenerateMap {
    private GameScreen gameScreen;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private MapHelper mapHelper;
    private World world;
    boolean[][] cellmap;

    List<Wall> walls = new ArrayList<>();
    List<Items> items = new ArrayList<>();
    private List<PlayerController> players = new ArrayList<>();

    public GenerateMap(GameScreen gameScreen){
        this.world = new World(new Vector2(0,0), false);
        this.gameScreen = gameScreen;
        this.mapHelper = new MapHelper(this);
        this.orthogonalTiledMapRenderer = mapHelper.setUpMap();
        generateRandomMap();
        initializePlayers();
    }

    private void generateRandomMap() {
        GameOfLife gameOfLife = new GameOfLife();
        cellmap = gameOfLife.generateMap();
        cellmap = MapCollision.collision(cellmap);

        for (int i = 0; i < tile_width; i++) {
            for (int j = 0; j < tile_height; j++) {
                int neighbours = gameOfLife.countAliveNeighbours(cellmap, i, j);
                if(cellmap[i][j]) {
                    Wall wall = new Wall(i, j, 32f, 32f, world);
                    walls.add(wall);
                    if (neighbours <= 2 || neighbours >= 6) {
                        double rand = random();
                        if (rand <= 0.1)
                            createSpeedPotion(i, j, 12 / PPM, 12 / PPM);
                        else if (rand >= 0.5 && rand <= 0.6)
                            createBombUpgrade(i, j, 12 / PPM, 12 / PPM);
                        else if (rand >= 0.9 && rand <= 1)
                            createAidKit(i, j, 12 / PPM, 12 / PPM);
                    }
                }
            }
        }
    }

    public void createSpeedPotion(int x, int y, float szerokosc, float wysokosc){
        SpeedPotion sp = new SpeedPotion(world, x, y);
        items.add(sp);
    }

    public void createAidKit(int x, int y, float szerokosc, float wysokosc){
        FirstAidKit fa = new FirstAidKit(world, x, y);
        items.add(fa);
    }

    public void createBombUpgrade(int x, int y, float szerokosc, float wysokosc){
        BombUpgrade bu = new BombUpgrade(world, x, y);
        items.add(bu);
    }

    private void initializePlayers() {
        // na mapie wydzielamy im specialnie niezajety teren w tej okolicy
        PlayerFactoryInterface playerFactory=new DefaultPlayerFactory();

        Player player1 = playerFactory.createPlayer(3*PPM, 2*PPM,world);
        PlayerViewInterface playerView1 = playerFactory.createPlayerView(player1);
        PlayerController playerController1 = playerFactory.createPlayerController(
                player1,playerView1,Input.Keys.UP,Input.Keys.DOWN,Input.Keys.LEFT,Input.Keys.RIGHT,Input.Keys.SPACE);
        players.add(playerController1);

        Player player2 = playerFactory.createPlayer(26*PPM, 27*PPM,world);
        PlayerViewInterface playerView2 = playerFactory.createPlayerView(player2);
        PlayerController playerController2 = playerFactory.createPlayerController(
                player2,playerView2,Input.Keys.W,Input.Keys.S,Input.Keys.A,Input.Keys.D,Input.Keys.F);
        players.add(playerController2);
    }

    public void removeWall(Wall body) {
        world.destroyBody(body.getBody());
        walls.remove(body);
    }

    public List<PlayerController> getPlayers() {
        return players;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Items> getItems() {
        return items;
    }

    public OrthogonalTiledMapRenderer getOrthogonalTiledMapRenderer() {
        return orthogonalTiledMapRenderer;
    }

    public World getWorld() {
        return world;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void dispose() {
        for (Wall body : walls)
            gameScreen.getWorld().destroyBody(body.getBody());
        mapHelper.mapa.dispose();
    }
}
