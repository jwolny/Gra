package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.model.ImageButtonUtils;

public enum ButtonsC {
    STARTBUTTON("Buttons/start_button.png","Buttons/start_down.png","Buttons/start_down.png",600,296),
    EXITBUTTON("Buttons/exit_button.png","Buttons/exit_down.png","Buttons/exit_down.png",600,296),
    MAP1("Buttons/plansza.png", "Buttons/plansza.png", "Buttons/plansza.png", 200, 200),

    ZEROBUTTON("Buttons/0_button_def.png","Buttons/0_button_clicked.png","Buttons/0_button_clicked.png",100,60 ),
    ONEBUTTON("Buttons/1_button_def.png","Buttons/1_button_clicked.png","Buttons/1_button_def.png",100,60 ),
    TWOBUTTON("Buttons/2_button_def.png","Buttons/2_button_clicked.png","Buttons/2_button_def.png",100,60 ),
    THREEBUTTON("Buttons/3_button_def.png","Buttons/3_button_clicked.png","Buttons/3_button_dis.png",100,60 ),
    GOBUTTON("Buttons/GO_button_def.png","Buttons/GO_button_clicked.png","Buttons/GO_button_dis.png",100,60 ),
    EXITBUTTON2("Buttons/exit_button_def.png","Buttons/exit_button_clicked.png","Buttons/exit_button_def.png",100,60 );

    private final String textureUpPath;
    private final String textureDownPath;
    private final String textureDisabledPath;
    private final float width;
    private final float height;

    ButtonsC(String textureUpPath, String textureDownPath, String textureDisabledPath, float width, float height) {
        this.textureUpPath = textureUpPath;
        this.textureDownPath = textureDownPath;
        this.textureDisabledPath = textureDisabledPath;
        this.width = width;
        this.height = height;
    }

    public String getTextureUpPath() {
        return textureUpPath;
    }

    public String getTextureDownPath() {
        return textureDownPath;
    }

    public String getTextureDisabledPath() {
        return textureDisabledPath;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }


    public ImageButton createImageButton() {
        TextureRegionDrawable up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(textureUpPath))));
        TextureRegionDrawable down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(textureDownPath))));
        TextureRegionDrawable disabled = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(textureDisabledPath))));
        return ImageButtonUtils.createImageButtonWithDis(up, down, disabled,this.getWidth(),this.getHeight());
    }

}
