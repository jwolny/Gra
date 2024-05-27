package com.mygdx.game.view;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.viewmodel.BombViewModel;

public class BombView {
        private BombViewModel viewModel;
        private Sound bombSound;

        public BombView(BombViewModel viewModel, Sound bombSound){
            this.viewModel = viewModel;
            this.bombSound=bombSound;
        }

        public void render(SpriteBatch batch){
            viewModel.render(batch);
        }

        public void dispose(){
            bombSound.play();
        }
}
