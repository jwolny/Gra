package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.others.Constants;
import com.mygdx.game.view.MenuScreens.MainMenuScreenV;
//import jdk.tools.jmod.Main;

public class BomberMan extends Game {
	OrthographicCamera orthographicCamera;
	public SpriteBatch batch;
    public BitmapFont font;
	@Override
	public void create() {
		this.orthographicCamera = new OrthographicCamera();
		this.orthographicCamera.setToOrtho(false, Constants.width, Constants.height);

		batch = new SpriteBatch();
        font = new BitmapFont();

		this.setScreen(new MainMenuScreenV(this));
		}


	@Override
	public void render () {
		super.render();
	}


	@Override
	public void dispose () {
        batch.dispose();
        font.dispose();
	}


}
