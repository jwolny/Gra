package com.mygdx.game.model.MapTools;

import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.model.Constants.*;

public class Wall {

    Body body;
    float x, y;
    float szerokosc, wysokosc;
    boolean destroyed = false;
    boolean toDestroy = false;
    private final World world;

    public Wall(int x, int y, float szerokosc, float wysokosc, World world) {
        this.x = x;
        this.y = y;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.world = world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((x * szerokosc + szerokosc / 2) / PPM, (y * wysokosc + wysokosc / 2) / PPM);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(szerokosc / 2 / PPM, wysokosc / 2 / PPM);
        FixtureDef fixturedef = new FixtureDef();
        fixturedef.filter.categoryBits = WALL_BIT;
        fixturedef.filter.maskBits = (short) (PLAYER_BIT | FLAME_BIT);
        fixturedef.shape = shape;
        fixturedef.friction = 0f;
        body.createFixture(fixturedef).setUserData(this);
        shape.dispose();
    }

    public void update(){
        if(toDestroy && !destroyed){
            world.destroyBody(body);
            destroyed = true;
        }
    }
    public Body getBody(){ return body; }

    public float getX() { return  x;}
    public float getY() { return  y;}

    public boolean isDestroyed(){
        return destroyed;
    }
    public void setDestroyed(){ toDestroy = true; }
}

