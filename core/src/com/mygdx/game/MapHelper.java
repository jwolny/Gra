package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

import java.util.Vector;

public class MapHelper {
    TiledMap mapa;
    GameScreen gameScreen;

    public MapHelper(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public OrthogonalTiledMapRenderer setUpMap() {
        mapa = new TmxMapLoader().load("mapa1_pop.tmx");
        parseMapObject(mapa.getLayers().get("Objects").getObjects());
        return new OrthogonalTiledMapRenderer(mapa);
    }

    private void parseMapObject(MapObjects mapObjects) {
        for (MapObject mapObject : mapObjects) {
            if (mapObject instanceof PolygonMapObject)
                creatStaticBody((PolygonMapObject) mapObject);

            if (mapObject instanceof RectangleMapObject) {
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                String rectangleName = mapObject.getName();
                if (rectangleName.equals("player")) {
                    Body body = BodyPlayer.createBody(
                            rectangle.getX() + rectangle.getWidth() / 2,
                            rectangle.getY() + rectangle.getHeight() / 2,
                            rectangle.getWidth(),
                            rectangle.getHeight(),
                            gameScreen.getWorld()
                    );
                    gameScreen.setPlayer(new Player(rectangle.getWidth(), rectangle.getHeight(), body, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.SPACE));
                }
            }
        }

    }

    private void creatStaticBody(PolygonMapObject polygonMapObject) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        body.createFixture(shape, 1000);
        shape.dispose();
    }

    private Shape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] verticies = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVerticies = new Vector2[verticies.length / 2];

        for (int i = 0; i < verticies.length / 2; i++) {
            Vector2 curr = new Vector2(verticies[i * 2] / 32.0f, verticies[i * 2 + 1] / 32.0f);
            worldVerticies[i] = curr;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVerticies);
        return shape;
    }
}
