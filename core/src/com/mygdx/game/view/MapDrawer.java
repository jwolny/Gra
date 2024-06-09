package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.model.Items.BombUpgrade;
import com.mygdx.game.model.Items.FirstAidKit;
import com.mygdx.game.model.Items.Items;
import com.mygdx.game.model.Items.SpeedPotion;
import com.mygdx.game.model.MapTools.GenerateMap;
import com.mygdx.game.model.MapTools.Wall;

import java.util.List;

import static com.mygdx.game.others.Constants.*;
import static com.mygdx.game.others.Textures.*;

public class MapDrawer {
    SpriteBatch spriteBatch;
    Texture wallTexture = WALL_PRISON_T.getTexture();
    Texture potionTexture = POTION_T.getTexture();
    Texture aidTexture = FIRST_AID_T.getTexture();
    Texture levelUpTexture = LEVEL_UP_T.getTexture();
    GenerateMap generateMap;
    List<Wall> walls;
    List<Items> items;

    public MapDrawer(GenerateMap generateMap){
        this.spriteBatch = new SpriteBatch();
        this.generateMap = generateMap;
        this.walls = generateMap.getWalls();
        this.items = generateMap.getItems();
    }

    public void render() {
        spriteBatch.setProjectionMatrix(generateMap.getGameScreen().camera.combined);
        spriteBatch.begin();
        generateMap.getOrthogonalTiledMapRenderer().render();
        for (Items item : items) {
            item.update();
            if(!item.isDestroyed()) {
                float xPos = item.getX() * PPM - item.getWidth() * PPM / 2;
                float yPos = item.getY() * PPM - item.getHeight() * PPM / 2;
                if(item instanceof SpeedPotion){ spriteBatch.draw(potionTexture, xPos, yPos, item_width, item_height); }
                if(item instanceof FirstAidKit){ spriteBatch.draw(aidTexture, xPos, yPos, item_width, item_height); }
                if(item instanceof BombUpgrade){ spriteBatch.draw(levelUpTexture, xPos, yPos, item_width, item_height); }
            }
        }
        for (Wall wall : walls) {
            wall.update();
            if(!wall.isDestroyed()) {
                float xPos = (wall.getX() * PPM);
                float yPos = (wall.getY() * PPM);
                spriteBatch.draw(wallTexture, xPos, yPos, PPM, PPM);
            }
        }
        spriteBatch.end();
    }

}
