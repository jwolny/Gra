package com.mygdx.game.viewmodel.BombTools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.model.Flame;
import com.mygdx.game.viewmodel.PlayerTools.PlayerViewModel;

public class BombViewModel{
    private Bomb bomb;
    private BombListener bombListener;


    public BombViewModel(Bomb bomb){
        this.bomb=bomb;
    }

    public void render(SpriteBatch batch){

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
                final Flame f = new Flame(bomb.getRadius(), bomb.getX(), bomb.getY(), bomb.getWorld());


                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        f.dispose();
                    }
                }, 0.1f);
            }
        }, 1f);
        dispose();
    }

    public Body getBody(){
        return bomb.getBody();
    }
}
