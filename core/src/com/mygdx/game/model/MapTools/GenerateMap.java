package com.mygdx.game.model.MapTools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.controller.*;
import com.mygdx.game.controller.PlayerTools.DefaultPlayerFactory;
import com.mygdx.game.controller.PlayerTools.PlayerController;
import com.mygdx.game.controller.PlayerTools.PlayerFactoryInterface;
import com.mygdx.game.model.Items.ItemFactory;
import com.mygdx.game.model.Items.ItemGenerator;
import com.mygdx.game.model.Items.Items;
import com.mygdx.game.model.PlayerTools.BodyPlayer;
import com.mygdx.game.model.PlayerTools.Player;
import com.mygdx.game.view.*;
import com.mygdx.game.view.MapAndGame.GameScreen;
import com.mygdx.game.view.PlayerTools.PlayerView;
import com.mygdx.game.view.PlayerTools.PlayerViewInterface;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.others.Constants.*;
import static java.lang.Math.random;

public class GenerateMap {
    private GameScreen gameScreen;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private World world;
    private boolean[][] cellmap;

    private List<Wall> walls = new ArrayList<>();
    private List<Items> items = new ArrayList<>();
    private List<PlayerController> players = new ArrayList<>();

    private ItemFactory speedPotionFactory = new ItemGenerator.SpeedPotionFactory();
    private ItemFactory firstAidKitFactory = new ItemGenerator.FirstAidKitFactory();
    private ItemFactory bombUpgradeFactory = new ItemGenerator.BombUpgradeFactory();
    private MapHelper mapHelper;

    public GenerateMap(GameScreen gameScreen) {
        this.world = new World(new Vector2(0, 0), false);
        this.gameScreen = gameScreen;
        this.mapHelper = new MapHelper(new TmxMapLoaderWrapper(), new PolygonMapObjectParser());
        this.orthogonalTiledMapRenderer = mapHelper.setUpMap("mapa1_pop.tmx", world);
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
                if (cellmap[i][j]) {
                    Wall wall = new Wall(i, j, 32f, 32f, world);
                    walls.add(wall);
                    if (neighbours <= 2 || neighbours >= 6) {
                        double rand = random();
                        if (rand <= 0.1)
                            createItem(speedPotionFactory, i, j, 22 / PPM, 22 / PPM);
                        else if (rand >= 0.5 && rand <= 0.6)
                            createItem(bombUpgradeFactory, i, j, 22 / PPM, 22 / PPM);
                        else if (rand >= 0.9 && rand <= 1)
                            createItem(firstAidKitFactory, i, j, 22 / PPM, 22 / PPM);
                    }
                }
            }
        }
    }

    private void createItem(ItemFactory factory, int x, int y, float width, float height) {
        Items item = factory.createItem(world, x, y, width, height);
        items.add(item);
    }

    private void initializePlayers() {
        createPlayer(3 * PPM, 2 * PPM, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.SPACE);
        createPlayer(26 * PPM, 27 * PPM, Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D, Input.Keys.F);
    }

    private void createPlayer(float x, float y, int up, int down, int left, int right, int action) {
        PlayerFactoryInterface playerFactory=new DefaultPlayerFactory();
        Player player=playerFactory.createPlayer(x,y,world);
        PlayerViewInterface playerView = playerFactory.createPlayerView(player);
        PlayerController playerController = playerFactory.createPlayerController(player, playerView, up, down, left, right, action);
        players.add(playerController);
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
        for (Wall body : walls) {
            gameScreen.getWorld().destroyBody(body.getBody());
        }
        mapHelper.dispose();
    }
}
