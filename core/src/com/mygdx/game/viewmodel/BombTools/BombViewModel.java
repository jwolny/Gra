package com.mygdx.game.viewmodel.BombTools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.model.BombTools.Bomb;
import com.mygdx.game.viewmodel.PlayerTools.PlayerViewModel;

public class BombViewModel{
    private Bomb bomb;
    private BombListener bombListener;;

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

    public void explode(){
        Timer.Task timer = new Timer.Task() {
            @Override
            public void run() {
                this.cancel();
                for (PlayerViewModel playerViewModel : PlayerViewModel.playerList) {
                    if (!playerViewModel.getPlayer().isDead() && playerViewModel.inRange(bomb.getX(), bomb.getY(), bomb.getRadius())) {
                        playerViewModel.modifyHP(-25.0f);
                    }
                }
                dispose();
            }
        };
        Timer.schedule(timer, 1, 1f);
    }

    public Body getBody(){
        return bomb.getBody();
    }
}
