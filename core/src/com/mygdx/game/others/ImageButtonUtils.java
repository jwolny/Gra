package com.mygdx.game.others;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ImageButtonUtils {
    public static ImageButton createImageButtonWithDis(TextureRegionDrawable up, TextureRegionDrawable down, TextureRegionDrawable dis,float sizeX, float sizeY){
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = up;
        style.down = down;
        style.over = down;
        style.disabled = dis;
        ImageButton res = new ImageButton(style);
        res.setSize(sizeX,sizeY);
        return res;
    }
    public static ImageButton addButton(ImageButton button, Table mainTable, float pad){
        mainTable.add(button).width(button.getWidth()).height(button.getHeight()).padBottom(pad);
        mainTable.row();
        return button;
    }
    public static ImageButton addButtonActor(ImageButton button, Stage stage, float posX, float posY){
        stage.addActor(button);
        button.setSize(button.getWidth(), button.getHeight());
        button.setPosition(posX,posY);
        return button;
    }
}
