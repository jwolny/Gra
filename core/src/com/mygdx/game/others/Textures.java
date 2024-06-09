package com.mygdx.game.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum Textures {

    POTION_T("potion.png"),
    FLAME_T("explosion.png"),
    FIRST_AID_T("apteczka.png"),
    BOMB_T("bomba.png"),
    LOGO_BOMBERMAN_T("logo.png"),
    WALL_PRISON_T("wall.png"),
    LEVEL_UP_T("levelup.png");
    private final String filePath;
    private Texture texture;

    Textures(String filePath) {
        this.filePath = filePath;
    }

    public Texture getTexture() {
        if (texture == null) {
            texture = new Texture(Gdx.files.internal(filePath));
        }
        return texture;
    }

    public static void disposeAll() {
        for (Textures textureEnum : Textures.values()) {
            if (textureEnum.texture != null) {
                textureEnum.texture.dispose();
                textureEnum.texture = null;
            }
        }
    }
}
