package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ImageButtonUtils {
    public static ImageButton createImageButton(TextureRegionDrawable up, TextureRegionDrawable down) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = up;
        style.down = down;
        style.over = down;
        return new ImageButton(style);
    }
    public static ImageButton addButton(Texture t,Texture s, Table mainTable, int width, int height, int pad){
        ImageButton button = ImageButtonUtils.createImageButton(new TextureRegionDrawable(new TextureRegion(t)),new TextureRegionDrawable(new TextureRegion(s)));
        mainTable.add(button).width(width).height(height).padBottom(pad);
        mainTable.row();
        return button;
    }
}
