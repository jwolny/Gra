package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;
import org.w3c.dom.Text;


public class MainMenuScreen implements Screen {
    final BomberMan game;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Texture startButtonTexture;
    private Texture exitButtonTexture;

    private TextureRegion startButtonTR;
    private TextureRegion exitButtonTR;
    public MainMenuScreen(final BomberMan game){
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        viewport = new FitViewport(Constants.width,Constants.height,camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        startButtonTexture = new Texture("start_button.png");
        exitButtonTexture = new Texture("exit_button.png");

        stage = new Stage(viewport,batch);
    }

    public void show(){
        Gdx.input.setInputProcessor(stage);


    }

    public void render(float d){
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        stage.act();
        stage.draw();

    }
    public void resize(int a,int b){

    }
    public void pause(){

    }
    public void resume(){

    }
    public void hide(){

    }
    public void dispose(){

    }

}
