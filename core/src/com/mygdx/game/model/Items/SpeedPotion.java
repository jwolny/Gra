package com.mygdx.game.model.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.GameScreen;
import com.mygdx.game.model.*;
import com.mygdx.game.model.Items.Items;

import static com.mygdx.game.model.Constants.ITEM_BIT;
import static com.mygdx.game.model.Constants.PPM;

public class SpeedPotion extends Items {
    public SpeedPotion(GameScreen screen, float x, float y) {
        super(screen, x, y);
        velocity = new Vector2(0, 0);
        texture = new Texture("potion.png");
        setRegion(texture);
        setSize(22 / PPM, 22 / PPM);
    }

    @Override
    public void defineItem() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / PPM);
        fdef.filter.categoryBits = ITEM_BIT;

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
    }

    // zmieniamy mu szybkosc chodzenia
    @Override
    public void use(Player mario) {
        destroy();
       // player.setSpeed() ...
    }

    @Override
    public void update() {
        super.update();
//        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
//        velocity.y = body.getLinearVelocity().y;
//        body.setLinearVelocity(velocity);
    }
}
