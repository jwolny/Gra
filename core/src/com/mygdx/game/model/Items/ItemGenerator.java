package com.mygdx.game.model.Items;

import com.badlogic.gdx.physics.box2d.World;

public class ItemGenerator {

    public static class SpeedPotionFactory implements ItemFactory {
        @Override
        public Items createItem(World world, int x, int y, float width, float height) {
            return new SpeedPotion(world, x, y);
        }
    }

    public static class FirstAidKitFactory implements ItemFactory {
        @Override
        public Items createItem(World world, int x, int y, float width, float height) {
            return new FirstAidKit(world, x, y);
        }
    }

    public static class BombUpgradeFactory implements ItemFactory {
        @Override
        public Items createItem(World world, int x, int y, float width, float height) {
            return new BombUpgrade(world, x, y);
        }
    }

}
