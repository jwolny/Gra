package com.mygdx.game.model.MapTools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.GameScreen;
import com.mygdx.game.model.Items.BombUpgrade;
import com.mygdx.game.model.Items.FirstAidKit;
import com.mygdx.game.model.Items.Items;
import com.mygdx.game.model.Items.SpeedPotion;
import com.mygdx.game.model.PlayerTools.BodyPlayer;
import com.mygdx.game.model.PlayerTools.Player;
import com.mygdx.game.viewmodel.PlayerTools.PlayerViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.model.Constants.*;
import static java.lang.Math.random;

public class MapHelper {
    TiledMap mapa;
    boolean[][] cellmap;
    GameScreen gameScreen;
    SpriteBatch spriteBatch;
    Texture texture;
    List<Wall> walls;
    List<Items> items;

    int alive_players;

    public MapHelper(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.spriteBatch = new SpriteBatch();
        this.texture = new Texture("wall.png");
        this.walls = new ArrayList<>();
        this.items = new ArrayList<>();
        spriteBatch.setProjectionMatrix(gameScreen.camera.combined); // rozmiar cegiel zgadza sie z wielkoscia pola
    }

    public OrthogonalTiledMapRenderer setUpMap() {
        mapa = new TmxMapLoader().load("mapa1_pop.tmx");
        parseMapObject(mapa.getLayers().get("Objects").getObjects());
        generateRandomMap();
        return new OrthogonalTiledMapRenderer(mapa);
    }

    public void render() {
        spriteBatch.setProjectionMatrix(gameScreen.camera.combined);
        spriteBatch.begin();
        for (Items item : items) {
            item.update();
            if(!item.isDestroyed()) {
                float xPos = item.getX() * PPM - item.getWidth() * PPM / 2;
                float yPos = item.getY() * PPM - item.getHeight() * PPM / 2;
                spriteBatch.draw(item.getTexture(), xPos, yPos, item_width, item_height);
            }
        }
        for (Wall wall : walls) {
            wall.update();
            if(!wall.destroyed) {
                float xPos = (wall.getX() * PPM);
                float yPos = (wall.getY() * PPM);
                spriteBatch.draw(texture, xPos, yPos, PPM, PPM);
            }
        }

        spriteBatch.end();
    }

    private void generateRandomMap() {
        GameOfLife gameOfLife = new GameOfLife();
        cellmap = gameOfLife.generateMap();
        cellmap = MapCollision.collision(cellmap);

        for (int i = 0; i < tile_width; i++) {
            for (int j = 0; j < tile_height; j++) {
                int neighbours = gameOfLife.countAliveNeighbours(cellmap, i, j);
                if(cellmap[i][j]) {
                   Wall wall = new Wall(i, j, 32f, 32f, gameScreen);
                   walls.add(wall);
                    if (neighbours == 2 || neighbours == 6) {       // troche w srodku a troche na brzegach
                        double rand = random();
                        if (rand <= 0.07)
                            createSpeedPotion(i, j, 12 / PPM, 12 / PPM);
                        else if (rand >= 0.5 && rand <= 0.57)
                            createBombUpgrade(i, j, 12 / PPM, 12 / PPM);
                        else if (rand >= 0.9 && rand <= 0.97)
                            createAidKit(i, j, 12 / PPM, 12 / PPM);
                    }
                }
            }
        }
        createBombUpgrade(2,2,12/PPM, 12/PPM);
        createBombUpgrade(3,1,12/PPM, 12/PPM);
        createBombUpgrade(1,2,12/PPM, 12/PPM);
        createBombUpgrade(2,26,12/PPM, 12/PPM);
    }


    public void createSpeedPotion(int x, int y, float szerokosc, float wysokosc){
        SpeedPotion sp = new SpeedPotion(gameScreen, x, y);
        items.add(sp);
    }

    public void createAidKit(int x, int y, float szerokosc, float wysokosc){
        FirstAidKit fa = new FirstAidKit(gameScreen, x, y);
        items.add(fa);
    }

    public void createBombUpgrade(int x, int y, float szerokosc, float wysokosc){
        BombUpgrade bu = new BombUpgrade(gameScreen, x, y);
        items.add(bu);
    }

    private void parseMapObject(MapObjects mapObjects) {
        for (MapObject mapObject : mapObjects) {
            if (mapObject instanceof PolygonMapObject) {
                createStaticBody((PolygonMapObject) mapObject);
            }

            if (mapObject instanceof RectangleMapObject) {
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                String rectangleName = mapObject.getName();
                if (rectangleName != null && rectangleName.equals("player")) {
                    Body body = BodyPlayer.createBody(
                            rectangle.getX(),
                            rectangle.getY(),
                            PPM - 3,    // ta szerokosc i wysokosc z mapy jest bez sensu imo ten moze chodzic normalnie
                            PPM - 3,
                            gameScreen.getWorld()
                    );
                    Body body2 = BodyPlayer.createBody(
                            3*PPM,
                            2*PPM,
                            PPM - 3,
                            PPM - 3,
                            gameScreen.getWorld()
                    );
                    Player player1=new Player(PPM-3, PPM-3, body, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.SPACE, gameScreen.getWorld(),100.0f);
                    PlayerViewModel playerViewModel1=new PlayerViewModel(player1);
                    gameScreen.setPlayer(playerViewModel1);
                    Player player2=new Player(PPM-3, PPM-3, body2, Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D, Input.Keys.F, gameScreen.getWorld(),100.0f);
                    PlayerViewModel playerViewModel2=new PlayerViewModel(player2);
                    gameScreen.setPlayer(playerViewModel2);
                }
            }
        }
    }

    private void createStaticBody(PolygonMapObject polygonMapObject) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        FixtureDef fixturedef = new FixtureDef();
        fixturedef.filter.categoryBits = INDESTRUCTIBLE_WALL_BIT;
        fixturedef.filter.maskBits = (short) (PLAYER_BIT | FLAME_BIT);
        fixturedef.shape = shape;
        body.createFixture(fixturedef);
        shape.dispose();
    }

    private Shape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; i++) {
            Vector2 curr = new Vector2(vertices[i * 2] / PPM, vertices[i * 2 + 1] / PPM);
            worldVertices[i] = curr;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }

    public void removeWall(Wall body) {
        gameScreen.getWorld().destroyBody(body.getBody());
        walls.remove(body);
    }

    public void dispose() {
        mapa.dispose();
        spriteBatch.dispose();
        texture.dispose();
        for (Wall body : walls) {
            gameScreen.getWorld().destroyBody(body.getBody());
        }
    }
}