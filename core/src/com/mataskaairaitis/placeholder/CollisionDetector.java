package com.mataskaairaitis.placeholder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by mataskairaitis on 18/05/16.
 */
public class CollisionDetector implements ContactListener {

    Random r;
    Array<Sound> collisionSounds;

    public CollisionDetector() {
        r = new Random();

        collisionSounds = new Array(new Sound[]{
                Gdx.audio.newSound(Gdx.files.internal("sounds/collision1.mp3")),
                Gdx.audio.newSound(Gdx.files.internal("sounds/collision2.mp3")),
                Gdx.audio.newSound(Gdx.files.internal("sounds/collision3.mp3"))
        });
    }


    @Override
    public void beginContact(Contact contact) {
        System.out.println("Collision start");
        collisionSounds.get(r.nextInt(collisionSounds.size)).play(0.1f);
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("Collision end");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
}
