package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;

public abstract class BombHelper {
    protected Body body;
    protected float x, y, radius;
    protected Texture texture;
    protected Sound bombSound;
    protected Timer.Task timer;
    protected World world;
    public BombHelper(Body body, float x, float y, float radius, World world) {
        this.body = body;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.bombSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bomboclat.mp3"));
        this.world = world;
    }
    public abstract void update();
    public abstract void draw();
    public abstract void render(SpriteBatch batch);

}
