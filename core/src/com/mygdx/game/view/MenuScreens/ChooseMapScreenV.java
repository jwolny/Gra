package com.mygdx.game.view.MenuScreens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.BomberMan;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.ImageButtonUtils;
import com.mygdx.game.view.ButtonsC;
import com.mygdx.game.viewmodel.MenuScreens.ChooseMapScreenVM;

public class ChooseMapScreenV implements Screen {
    private final ChooseMapScreenVM VM;

    final BomberMan game;
    protected Stage stage;
    private Viewport viewport;
    public final OrthographicCamera camera;
    private Table chooseTable;
    private ImageButton ExitButton = ButtonsC.EXITBUTTON2.createImageButton();
    private ImageButton OneButtonP = ButtonsC.ONEBUTTON.createImageButton();
    private ImageButton OneButtonB = ButtonsC.ONEBUTTON.createImageButton();
    private ImageButton TwoButtonP = ButtonsC.TWOBUTTON.createImageButton();
    private ImageButton TwoButtonB = ButtonsC.TWOBUTTON.createImageButton();
    public ImageButton ThreeButtonB = ButtonsC.THREEBUTTON.createImageButton();
    public ImageButton ZeroButtonB = ButtonsC.ZEROBUTTON.createImageButton();
    public ImageButton GoButton = ButtonsC.GOBUTTON.createImageButton();


    public ChooseMapScreenV(final BomberMan game){
        this.game = game;
        camera = new OrthographicCamera();
        VM = new ChooseMapScreenVM(game,this);
        camera.setToOrtho(false,960,960);

    }

    @Override
    public void show() {

        viewport = new ExtendViewport(Constants.width,Constants.height,camera);
        stage = new Stage(viewport);
        VM.playMenuMusic("Music/main_menu_music.wav");
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.font;
        labelStyle.font.getData().setScale(3);
        Label label = new Label("Wybierz mape", labelStyle);
        label.setPosition(Constants.width/2 - 100, Constants.height/2 + 350);
        Label player = new Label("Liczba graczy:", labelStyle);
        player.setPosition(100, 400);
        Label bots = new Label("liczba botów: ", labelStyle);
        bots.setPosition(100,250);

        chooseTable = new Table();
        chooseTable.setFillParent(true);
        stage.addActor(chooseTable);
        stage.addActor(label);
        stage.addActor(player);
        stage.addActor(bots);
        ImageButtonUtils.addButtonActor(ButtonsC.MAP1.createImageButton() ,stage, 100, 600).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onMapButtonClicked("prison.tmx");
            }
        });
        ImageButtonUtils.addButtonActor(ExitButton, stage,0, 0).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onExitButtonClicked();
            }
        });
        ImageButtonUtils.addButtonActor(ZeroButtonB, stage,100, 200).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onBotButtonClicked(0,ZeroButtonB);
            }
        });
        ImageButtonUtils.addButtonActor(OneButtonB, stage,220, 200).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onBotButtonClicked(1,OneButtonB);
            }
        });
        ImageButtonUtils.addButtonActor(TwoButtonB, stage,340, 200).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onBotButtonClicked(2,TwoButtonB);
            }
        });
        ImageButtonUtils.addButtonActor(ThreeButtonB, stage,460, 200).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onBotButtonClicked(3,ThreeButtonB);
            }
        });
        ImageButtonUtils.addButtonActor(OneButtonP, stage,100, 300).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onPlayerButtonClicked(1,OneButtonP);
            }
        });
        ImageButtonUtils.addButtonActor(TwoButtonP, stage,220, 300).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onPlayerButtonClicked(2,TwoButtonP);
            }
        });
        ImageButtonUtils.addButtonActor(GoButton, stage,960, 0).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                VM.onGoButtonClicked(GoButton);
            }
        });
        GoButton.setDisabled(VM.goDisabled());
        Gdx.input.setInputProcessor(stage);
        chooseTable.setPosition(-400,180);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        chooseTable.clear();
        stage.dispose();
    }
}
