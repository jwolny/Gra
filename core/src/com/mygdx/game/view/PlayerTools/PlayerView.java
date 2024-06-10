    package com.mygdx.game.view.PlayerTools;

    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.Sprite;
    import com.badlogic.gdx.graphics.g2d.TextureRegion;
    import com.mygdx.game.model.PlayerTools.Player;
    import com.mygdx.game.view.MapAndGame.GameScreen;

    import static com.mygdx.game.others.Constants.PPM;
    import static com.mygdx.game.others.Textures.PLAYER_T;

    public class PlayerView implements PlayerViewInterface {
        private final Player player;
        private Sprite sprite;
        public PlayerView(Player player) {
            this.player = player;
            Texture playerImage = PLAYER_T.getTexture();
            TextureRegion textureRegion = new TextureRegion(playerImage, 0, 0, 32, 32);
            sprite=new Sprite(textureRegion);
            player.getBody().setUserData(sprite);
        }

        public void render(){
            if(sprite==null)
                return;
            sprite.setPosition(player.getPosition().x*PPM-player.getWidth()/2, player.getPosition().y*PPM-player.getHeight()/2);
            GameScreen.getBatch().begin();
            sprite.draw(GameScreen.getBatch());
            GameScreen.getBatch().end();
        }

        public void dispose() {
            sprite=null;
        }

        public void update(){
            dispose();
        }
    }
