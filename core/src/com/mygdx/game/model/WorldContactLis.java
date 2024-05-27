package com.mygdx.game.model;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.model.PlayerTools.Player;

import static com.mygdx.game.model.Constants.*;

public class WorldContactLis implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        System.out.println("kowow");
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        System.out.println(fixtureB.getFilterData().categoryBits);
        System.out.println(fixtureA.getFilterData().categoryBits);


        if(isPlayer(fixtureA) && isFlame(fixtureB)){
            System.out.println("sraka");
            ((Player)fixtureA.getUserData()).modifyHP(-25f);
            System.out.println(((Player)fixtureA.getUserData()).getHP());
        }
        if(isFlame(fixtureA) && isPlayer(fixtureB)){
            System.out.println("sraka2");
            ((Player)fixtureB.getUserData()).modifyHP(-25f);
            System.out.println(((Player)fixtureB.getUserData()).getHP());
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
