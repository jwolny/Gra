package com.mygdx.game.model.MapTools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


import static com.mygdx.game.model.Constants.*;

public class MapHelper {
    TiledMap mapa;
    //GameScreen gameScreen;
    GenerateMap generateMap;
    // TODO to nie tutaj
//    SpriteBatch spriteBatch;
    // TODO to nie tutaj
//    Texture texture;
    // TODO to nie tutaj

    public MapHelper(GenerateMap generateMap) {
        this.generateMap = generateMap;
        //this.gameScreen = gameScreen;
        //this.spriteBatch = new SpriteBatch();
        //this.texture = new Texture("wall.png");
        //this.walls = new ArrayList<>();
        //this.items = new ArrayList<>();
        //spriteBatch.setProjectionMatrix(gameScreen.camera.combined); // rozmiar cegiel zgadza sie z wielkoscia pola
    }

    public OrthogonalTiledMapRenderer setUpMap() {
        mapa = new TmxMapLoader().load("mapa1_pop.tmx");
        parseMapObject(mapa.getLayers().get("Objects").getObjects());
        return new OrthogonalTiledMapRenderer(mapa);
    }

/*
    // TODO to nie tutaj
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


*/
    private void parseMapObject(MapObjects mapObjects) {
        for (MapObject mapObject : mapObjects)
            if (mapObject instanceof PolygonMapObject)
                createStaticBody((PolygonMapObject) mapObject);
    }

    private void createStaticBody(PolygonMapObject polygonMapObject) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = generateMap.getWorld().createBody(bodyDef);
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
}