package com.mygdx.game.model.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.PlayerTools.Player;

import static com.mygdx.game.others.Constants.*;

public class FirstAidKit extends Items {
    public FirstAidKit(World world, float x, float y) {
        super(world, x, y);
        velocity = new Vector2(0, 0);
        texture = new Texture("apteczka.png");
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
        fdef.density = 0;
        fdef.filter.maskBits = PLAYER_BIT;

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
        shape.dispose();
    }

    @Override
    public void use(Player player) {
        destroy();
        player.modifyHP(100);
    }
}
