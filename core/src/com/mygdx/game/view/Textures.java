package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum Textures {

    FLAME_T("explosion.png"),
    FIRST_AID_T("apteczka.png"),
    BOMB_T("bomba.png"),
    LOGO_BOMBERMAN_T("logo.png"),
    WALL_PRISON_T("wall.png"),
    LEVEL_UP_T("levelup.png");
    private final Texture texture;

    Textures(String s){
        this.texture = new Texture(Gdx.files.internal(s));
    }
}
