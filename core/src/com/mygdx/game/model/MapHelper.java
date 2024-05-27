package com.mygdx.game.model;

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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.GameScreen;
import com.mygdx.game.model.Items.ItemDef;
import com.mygdx.game.model.Items.Items;
import com.mygdx.game.model.Items.SpeedPotion;
import com.mygdx.game.viewmodel.PlayerViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.model.Constants.*;

public class MapHelper {
    Texture speedPotionTexture;
    TiledMap mapa;
    GameScreen gameScreen;
    SpriteBatch spriteBatch;
    Texture texture;
    List<Body> bodies;

    List<Items> items;

    public MapHelper(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.spriteBatch = new SpriteBatch();
        this.texture = new Texture("wall.png");
        this.speedPotionTexture = new Texture("potion.png"); // Dodajemy teksturę dla SpeedPotion
        this.bodies = new ArrayList<Body>();
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
        for (Body body : bodies) {
            float xPos = (body.getPosition().x * PPM - PPM / 2);
            float yPos = (body.getPosition().y * PPM - PPM / 2);
            spriteBatch.draw(texture, xPos , yPos, PPM, PPM);
        }
        for (Items item : items) {
            if (item instanceof SpeedPotion) {      // TODO: usuwamy z mapy usuniete itemy
                float xPos = item.getX() * PPM - item.getWidth() * PPM / 2;
                float yPos = item.getY() * PPM - item.getHeight() * PPM / 2;
                spriteBatch.draw(speedPotionTexture, xPos, yPos, 0.7f * PPM, 0.7f * PPM); // Rysujemy teksturę SpeedPotion
            }
        }
        spriteBatch.end();
    }

    private void generateRandomMap() {
        GameOfLife gameOfLife = new GameOfLife();
        boolean[][] cellmap = gameOfLife.generateMap();
        cellmap = MapCollision.collision(cellmap);

        for (int i = 0; i < tile_width; i++) {
            for (int j = 0; j < tile_height; j++) {
                if(cellmap[i][j]) {
                    setRectangleBody(i, j, 32f, 32f);
                }
                if(i % 13 == 0 && j % 13 == 0) {
                    createSpeedPotion(i, j, 12 / PPM, 12 / PPM); // Tworzymy SpeedPotion
                }
            }
        }
    }

    private void setRectangleBody(int x, int y, float szerokosc, float wysokosc) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((x * szerokosc + szerokosc / 2) / PPM, (y * wysokosc + wysokosc / 2) / PPM);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getWorld().createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(szerokosc / 2 / PPM, wysokosc / 2 / PPM);
        body.createFixture(shape, 0.0f);
        shape.dispose();

        bodies.add(body);
    }

    public void createSpeedPotion(int x, int y, float szerokosc, float wysokosc){
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        bodyDef.position.set(x + 0.5f, y + 0.5f);
//
//        Body body = gameScreen.getWorld().createBody(bodyDef);
//
//        PolygonShape polygonShape = new PolygonShape();
//        polygonShape.setAsBox(szerokosc / 2, wysokosc / 2);
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = polygonShape;
//        fixtureDef.filter.categoryBits = ITEM_BIT;
//        fixtureDef.filter.maskBits = PLAYER_BIT;
//        fixtureDef.isSensor = true;
//        body.createFixture(fixtureDef);

        SpeedPotion sp = new SpeedPotion(gameScreen, x, y);
        items.add(sp);

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
                            rectangle.getX() + rectangle.getWidth() / 2,
                            rectangle.getY() + rectangle.getHeight() / 2,
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
                    gameScreen.setPlayer(new PlayerViewModel(new Player(rectangle.getWidth(), rectangle.getHeight(), body, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.SPACE, gameScreen.getWorld(),100.0f)));
                    gameScreen.setPlayer(new PlayerViewModel(new Player(1f, 1f, body2, Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D, Input.Keys.F, gameScreen.getWorld(),100.0f)));
                }
            }
        }
    }

    private void createStaticBody(PolygonMapObject polygonMapObject) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        body.createFixture(shape, 1000);
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

    public void removeBody(Body body) {
        gameScreen.getWorld().destroyBody(body);
        bodies.remove(body);
    }

    public void dispose() {
        mapa.dispose();
        spriteBatch.dispose();
        texture.dispose();
        for (Body body : bodies) {
            gameScreen.getWorld().destroyBody(body);
        }
    }
}