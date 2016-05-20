package com.mataskaairaitis.mazescape.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by mataskairaitis on 18/05/16.
 */
public class CollisionDetector implements ContactListener {

    Random r;
    Array<Sound> collisionSounds;
    Sound activeSound;

    /**
     * Constructs a new CollisionDetector that contains
     * collision listeners. Loads collision audio files.
     */
    public CollisionDetector() {
        r = new Random();

        collisionSounds = new Array(new Sound[]{
                Gdx.audio.newSound(Gdx.files.internal("sounds/collision1.mp3")),
                Gdx.audio.newSound(Gdx.files.internal("sounds/collision2.mp3")),
                Gdx.audio.newSound(Gdx.files.internal("sounds/collision3.mp3"))
        });
    }

    /**
     * Stops the sound that is currently playing.
     */
    public void stop() {
        activeSound.stop();
    }


    /**
     * Plays a sound on collision.
     * {@inheritDoc}
     */
    @Override
    public void beginContact(Contact contact) {
        activeSound = collisionSounds.get(r.nextInt(collisionSounds.size));
        activeSound.play(0.1f);

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Gdx.app.log("beginContact", "between " + fixtureA.toString() + " and " + fixtureB.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endContact(Contact contact) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
}
