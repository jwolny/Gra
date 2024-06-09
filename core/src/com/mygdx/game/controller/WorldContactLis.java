package com.mygdx.game.controller;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.model.Items.FirstAidKit;
import com.mygdx.game.model.Items.Items;
import com.mygdx.game.model.MapTools.Wall;
import com.mygdx.game.model.PlayerTools.Player;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.model.Constants.*;

public class WorldContactLis implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        System.out.println("-------");
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        if(fixtureB.getFilterData().categoryBits == WALL_BIT || fixtureA.getFilterData().categoryBits == WALL_BIT) {
            System.out.println("wall collision");
            System.out.println(fixtureB.getFilterData().categoryBits);
            System.out.println(fixtureA.getFilterData().categoryBits);
        }

        if(isPlayer(fixtureA) && isFlame(fixtureB)){
            System.out.println("Player - Flame");
            if(fixtureA.getUserData() != null) {
                ((Player) fixtureA.getUserData()).modifyHP(-25f);
                System.out.println(((Player) fixtureA.getUserData()).getHP());
            }
        }
        if(isFlame(fixtureA) && isPlayer(fixtureB)){
            System.out.println("Flame - Player");
            if(fixtureB.getUserData() != null){
            ((Player)fixtureB.getUserData()).modifyHP(-25f);
            System.out.println(((Player)fixtureB.getUserData()).getHP());}
        }
        if(isFlame(fixtureA) && isWall(fixtureB)){
            System.out.println("wall - flame");
            if(fixtureB.getUserData() != null){
                ((Wall)fixtureB.getUserData()).setDestroyed();
            }
        }
        if(isFlame(fixtureB) && isWall(fixtureA)){
            System.out.println("wall - flame");
            if(fixtureA.getUserData() != null){
                ((Wall)fixtureA.getUserData()).setDestroyed();
            }
        }
        // TODO: naprawić to
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
