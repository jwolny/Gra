package com.mygdx.game.model.Items;

import com.badlogic.gdx.physics.box2d.World;

public interface ItemFactory {
    Items createItem(World world, int x, int y, float width, float height);
}

