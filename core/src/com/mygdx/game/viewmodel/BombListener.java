package com.mygdx.game.viewmodel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface BombListener {
    void dispose();
    void render(SpriteBatch batch);
}
