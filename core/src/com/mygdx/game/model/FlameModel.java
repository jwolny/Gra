package com.mygdx.game.model;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.view.FlameView;

import static com.mygdx.game.model.Constants.FLAME_BIT;
import static com.mygdx.game.model.Constants.PPM;

public class FlameModel {
    private World world;
    private Body body;
    private float size;
    private float posX;
    private float posY;
    private boolean destroyed = false;

    public FlameModel(float size, float posX, float posY, World world){
        this.world = world;
        this.size = size;
        this.posX = posX;
        this.posY = posY;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(posX / PPM, posY / PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size / 2, 0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 1000;
        fixtureDef.filter.categoryBits = FLAME_BIT;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData(this);

        shape.setAsBox(0.5f, size / 2);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData(this);

        shape.dispose();
    }

    public Body getBody() {
        return body;
    }

    public void dispose() {
        world.destroyBody(body);
        destroyed = true;
    }
    public float getPosX(){
        return posX;
    }
    public float getPosY(){
        return posY;
    }
    public float getSize(){
        return size;
    }
    public boolean getDestroyed(){
        return destroyed;
    }
}
