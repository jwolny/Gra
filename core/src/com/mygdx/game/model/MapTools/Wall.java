package com.mygdx.game.model.MapTools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.GameScreen;

import static com.mygdx.game.model.Constants.*;

public class Wall {
    GameScreen gameScreen;
    Body body;
    float x, y;
    float szerokosc, wysokosc;
    boolean destroyed = false;


    public Wall(int x, int y, float szerokosc, float wysokosc, GameScreen gameScreen) {
        this.x = x;
        this.y = y;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.gameScreen = gameScreen;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((x * szerokosc + szerokosc / 2) / PPM, (y * wysokosc + wysokosc / 2) / PPM);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = gameScreen.getWorld().createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(szerokosc / 2 / PPM, wysokosc / 2 / PPM);
        FixtureDef fixturedef = new FixtureDef();
        fixturedef.filter.categoryBits = WALL_BIT;
        fixturedef.filter.maskBits = (short) (PLAYER_BIT | FLAME_BIT);
        fixturedef.shape = shape;
        fixturedef.friction = 100000f;
        body.createFixture(fixturedef);
        shape.dispose();
    }
    public Body getBody(){ return body; }

    public float getX() { return  x;}
    public float getY() { return  y;}

    public void setDestroyed(){ destroyed = true; }
}

