package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.Constants.PPM;

public class BodyBomb {
    public static Body createBody(float x, float y, float radius, World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x/PPM, y/PPM);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape=shape;
        fixtureDef.density = 0;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
}
