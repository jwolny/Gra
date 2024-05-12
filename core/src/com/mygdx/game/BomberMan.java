package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.MissingFormatArgumentException;
//import jdk.tools.jmod.Main;

public class BomberMan extends Game {
	int width, height;
	OrthographicCamera orthographicCamera;
	SpriteBatch batch;
	Texture img;
	Music gameMusic;
	Sound explosionSound;
	@Override
	public void create() {
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		this.orthographicCamera = new OrthographicCamera();
		this.orthographicCamera.setToOrtho(false, width, height);

		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");

		//gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Gra.mp3"));
		//explosionSound = Gdx.audio.newSound(Gdx.files.internal("Menu.wav"));

		this.setScreen(new MainMenuScreen(this));
		}


	@Override
	public void render () {
		super.render();
	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();

	}


}
