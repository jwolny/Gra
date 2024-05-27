package com.mygdx.game.model;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.model.PlayerTools.Player;

import static com.mygdx.game.model.Constants.*;

public class WorldContactLis implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(isPlayer(fixtureA) && isFlame(fixtureB)){
            if(fixtureA.getUserData() != null && fixtureA.getUserData() instanceof Player)
            ((Player)fixtureA.getUserData()).modifyHP(-25f);
        }
        if(isFlame(fixtureA) && isPlayer(fixtureB)){
            if(fixtureB.getUserData() != null && fixtureB.getUserData() instanceof Player)
            ((Player)fixtureB.getUserData()).modifyHP(-25f);
        }
        /*if(isFlame(fixtureA) && isWall(fixtureB)){

        }*/
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

}
