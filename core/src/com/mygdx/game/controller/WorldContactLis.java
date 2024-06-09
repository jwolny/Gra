package com.mygdx.game.controller;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.model.Items.Items;
import com.mygdx.game.model.MapTools.Wall;
import com.mygdx.game.model.PlayerTools.Player;

import static com.mygdx.game.others.Constants.*;

public class WorldContactLis implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(isPlayer(fixtureA) && isFlame(fixtureB)){
            if(fixtureA.getUserData() != null) {
                ((Player) fixtureA.getUserData()).modifyHP(-25f);
                System.out.println(((Player) fixtureA.getUserData()).getHP());
            }
        }
        if(isFlame(fixtureA) && isPlayer(fixtureB)){
            if(fixtureB.getUserData() != null){
            ((Player)fixtureB.getUserData()).modifyHP(-25f);
            System.out.println(((Player)fixtureB.getUserData()).getHP());}
        }
        if(isFlame(fixtureA) && isWall(fixtureB)){
            if(fixtureB.getUserData() != null){
                ((Wall)fixtureB.getUserData()).setDestroyed();
            }
        }
        if(isFlame(fixtureB) && isWall(fixtureA)){
            if(fixtureA.getUserData() != null){
                ((Wall)fixtureA.getUserData()).setDestroyed();
            }
        }
        if(isItem(fixtureA) && isPlayer(fixtureB)){
            ((Items)fixtureA.getUserData()).use((Player)fixtureB.getUserData());
        }
        if(isItem(fixtureB) && isPlayer(fixtureA)){
            ((Items)fixtureB.getUserData()).use((Player)fixtureA.getUserData());
        }
    }
    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public boolean isWall(Fixture f){
        return (f.getFilterData().categoryBits == WALL_BIT);
    }
    public boolean isFlame(Fixture f){
        return (f.getFilterData().categoryBits == FLAME_BIT);
    }
    public boolean isPlayer(Fixture f){
        return f.getFilterData().categoryBits == PLAYER_BIT;
    }
    public boolean isItem(Fixture f){
        return f.getFilterData().categoryBits == ITEM_BIT;
    }

}
