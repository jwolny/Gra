package com.mygdx.game.model;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.model.Items.Items;
import com.mygdx.game.model.Items.SpeedPotion;
import com.mygdx.game.model.PlayerTools.Player;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.model.Constants.*;

public class WorldContactLis implements ContactListener {

    private List<Body> bodiesToDestroy = new ArrayList<>();
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
            System.out.println("sraka");
            if(fixtureA.getUserData() != null) {
                ((Player) fixtureA.getUserData()).modifyHP(-25f);
                System.out.println(((Player) fixtureA.getUserData()).getHP());
            }
        }
        if(isFlame(fixtureA) && isPlayer(fixtureB)){
            System.out.println("sraka2");
            if(fixtureB.getUserData() != null){
            ((Player)fixtureB.getUserData()).modifyHP(-25f);
            System.out.println(((Player)fixtureB.getUserData()).getHP());}
        }
        /*if(isFlame(fixtureA) && isWall(fixtureB)){
            System.out.println("sraka3");
            bodiesToDestroy.add(fixtureB.getBody());
        }
        if(isFlame(fixtureB) && isWall(fixtureA)){
            System.out.println("sraka3");
            bodiesToDestroy.add(fixtureB.getBody());
        }
        */ // TODO: naprawić to
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
    public void destroyFlaggedBodies() {
        for (Body body : bodiesToDestroy) {
            body.getWorld().destroyBody(body);
        }
        bodiesToDestroy.clear();
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
