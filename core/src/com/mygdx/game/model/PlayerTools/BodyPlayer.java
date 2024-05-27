package com.mygdx.game.model.PlayerTools;

import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.model.Constants.PPM;

public class BodyPlayer {
    public static Body createBody(float x, float y, float width, float height, World world)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/PPM, y/PPM);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2/PPM, height/2/PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape=shape;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
}
