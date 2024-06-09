package com.mygdx.game.model.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.PlayerTools.Player;

import static com.mygdx.game.others.Constants.PPM;

public abstract class Items extends Sprite {
    protected World world;
    protected Vector2 velocity;
    // bo mozego go zniszczyc dopiero w update
    protected boolean toDestroy;
    protected boolean destroyed;
    protected Body body;
    protected Texture texture;

    public Items(World world, float x, float y){
        this.world = world;
        toDestroy = false;
        destroyed = false;

        setPosition(x + 0.5f, y + 0.5f);
        // robimy go 24x24, zeby sie troche odroznial
        setBounds(getX(), getY(), 24 / PPM, 24 / PPM);
        defineItem();
    }

    public abstract void defineItem();
    public abstract void use(Player player);

    public void update(){
        if(toDestroy && !destroyed){
            world.destroyBody(body);
            destroyed = true;
        }
    }

    public void draw(Batch batch){
        if(!destroyed)
            super.draw(batch);
    }

    public boolean isDestroyed(){
        return destroyed;
    }


    public void destroy(){
        toDestroy = true;
    }

    public Texture getTexture(){return texture;}

}
