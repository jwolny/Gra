package com.mygdx.game.model.Items;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.PlayerTools.Player;

import static com.mygdx.game.others.Constants.PPM;

public class FirstAidKit extends Items {
    public FirstAidKit(World world, float x, float y) {
        super(world, x, y, 32/PPM , 32/PPM);
        velocity = new Vector2(0, 0);
        //setSize(32 / PPM, 32 / PPM);
    }

    @Override
    public void use(Player player) {
        destroy();
        player.modifyHP(100);
    }
}
