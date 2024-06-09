package com.mygdx.game.viewmodel.BombTools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.GameScreen;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.model.FlameModel;
import com.mygdx.game.view.BombView;
import com.mygdx.game.view.FlameView;

public class BombViewModel{
    private Bomb bomb;
    private BombListener bombListener;


    public BombViewModel(Bomb bomb){

        this.bomb=bomb;
        bombListener=new BombView(this, "Sounds/bomboclat.mp3");
    }

    public void render(SpriteBatch batch){
        bombListener.render(batch);
    }

    public void setBombListener(BombListener bombListener){
        this.bombListener=bombListener;
    }

    public void dispose(){
        if(bombListener!=null)
            bombListener.dispose();
    }

    public void explode() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                FlameModel f = new FlameModel(bomb.getRadius(), bomb.getX(), bomb.getY(), bomb.getWorld());
                FlameView fv = new FlameView(f);

                dispose();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        f.dispose();
                    }
                }, 0.1f);
            }
        }, 1f);
    }

    public Body getBody(){
        return bomb.getBody();
    }

    public World getWorld(){
        return bomb.getWorld();
    }
}
