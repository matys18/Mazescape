package com.mataskaairaitis.mazescape.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mataskaairaitis.mazescape.screens.GameScreen;

import java.util.Random;

/**
 * Listens for collisions between objects and performs actions based on
 * what objects are colliding.
 *
 * @author Matas Kairaitis
 * @version 2016-05-23
 */
public class CollisionDetector implements ContactListener {

    Random r;
    Array<Sound> collisionSounds;
    Sound activeSound;
    GameScreen game;

    /**
     * Constructs a new CollisionDetector that contains
     * collision listeners. Loads collision audio files.
     */
    public CollisionDetector(GameScreen game) {
        this.game = game;

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
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Fixture player = game.getPlayer().getFixture();
        Fixture goal = game.getGoal().getFixture();

        if ((fixtureA.equals(player) && fixtureB.equals(goal)) ||
                (fixtureB.equals(player) && fixtureA.equals(goal))) {
            game.win();
        } else {
            activeSound = collisionSounds.get(r.nextInt(collisionSounds.size));
            activeSound.play(0.1f);
        }
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
