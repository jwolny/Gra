package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.GameScreen;

import static com.mygdx.game.model.Constants.*;

public class Flame {
    Texture texture = new Texture(Gdx.files.internal("explosion.png"));
    private World world;
    private Body body;
    private float size;

    private float posX;
    private float posY;

    public Flame(float size, float posX, float posY, World world){
        this.world = world;
        this.size = size;
        this.posX = posX;
        this.posY = posY;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(posX / PPM ,posY / PPM);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size/2,1);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.0f;
        fixtureDef.filter.categoryBits = FLAME_BIT;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef);
        shape.setAsBox(1,size/2);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
        body.setUserData(texture);
    }

    public void dispose(){
        for(Fixture v : body.getFixtureList()){
            body.destroyFixture(v);
        }
    }
}
