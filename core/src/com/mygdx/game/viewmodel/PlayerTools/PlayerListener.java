package com.mygdx.game.viewmodel.PlayerTools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface PlayerListener {
    void dispose();
    void render(SpriteBatch batch);
}
