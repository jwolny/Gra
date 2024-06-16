package com.mygdx.game.model.Items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.model.PlayerTools.Player;

import static com.mygdx.game.others.Constants.*;
import static com.mygdx.game.others.Constants.FLAME_BIT;

public abstract class Items {
    float x, y;
    float szerokosc, wysokosc;
    protected World world;
    protected Vector2 velocity;
    protected boolean toDestroy;
    protected boolean destroyed;
    protected Body body;

    public Items(World world1, float x, float y, float szerokosc, float wysokosc){
        this.world = world1;
        toDestroy = false;
        destroyed = false;

        this.x = x;
        this.y = y;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x + 0.5f, y + 0.5f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(szerokosc / PPM * 6, wysokosc / PPM * 6);
        FixtureDef fixturedef = new FixtureDef();
        fixturedef.filter.categoryBits = ITEM_BIT;
        fixturedef.filter.maskBits = PLAYER_BIT;
        fixturedef.shape = shape;
        fixturedef.density = 0;
        body.createFixture(fixturedef).setUserData(this);
        shape.dispose();
/*
        // robimy go 24x24, zeby sie troche odroznial
        setBounds(getX(), getY(), 24 / PPM, 24 / PPM);
        defineItem();

 */
    }

    //public abstract void defineItem();
    public abstract void use(Player player);

    public void update(){
        if(toDestroy && !destroyed){
            world.destroyBody(body);
            destroyed = true;
        }
    }

    public boolean isDestroyed(){
        return destroyed;
    }

    public void destroy(){
        toDestroy = true;
    }

    public float getX() {
        return x + 0.5f;
    }

    public float getY() {
        return y + 0.5f;
    }

    public float getWidth() {
        return szerokosc;
    }

    public float getHeight() {
        return wysokosc;
    }
    public void setSize(float w, float h){
        wysokosc = h;
        szerokosc = w;
    }
}
