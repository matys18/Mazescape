package com.mataskaairaitis.mazescape.screens;

import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mataskaairaitis.mazescape.input.*;
import com.mataskaairaitis.mazescape.levels.*;
import com.mataskaairaitis.mazescape.models.*;
import com.mataskaairaitis.mazescape.game.*;

import java.util.Random;

/**
 * The Screen that represents the actual game.
 * @author Matas Kairaitis
 * @version 2016-06-19
 */
public class GameScreen extends ParentScreen {

    OrthographicCamera camera;
    //Box2DDebugRenderer renderer;
    FPSLogger fpsLogger;
    World world;
    RayHandler rayHandler;
    ShapeRenderer shapes;
    Music ambientMusic;

    PlayerModel player;
    LevelModel level;

    /**
     * Constructs a new GameScreen.
     * @param game  The Game of which this Screen is a part of
     */
    public GameScreen(Mazescape game) {
    	super(game, GameControl.class);

        // Create the camera and set it's position
        camera = new OrthographicCamera(width * 0.2f, height * 0.2f);
        camera.position.set(width * 0.5f, height * 0.5f, 0);
        camera.update();

        // Create an OpenGL shape renderer and attach the camera to it
        shapes = new ShapeRenderer();
        shapes.setProjectionMatrix(camera.combined);

        // Fps logger and box2d renderer
        //renderer = new Box2DDebugRenderer();
        fpsLogger = new FPSLogger();

        // Create the box2d world
        world = new World(new Vector2(0, 0), true);

        world.setContactListener(new CollisionDetector());

        // Lighting settings
        rayHandler = new RayHandler(world);
        rayHandler.setCombinedMatrix(camera.combined);

        // Create a player instance
        player = new PlayerModel(510f, 270f, 6f, world, rayHandler, Color.ORANGE);

        // Load obsticles for this level
        level = new Level1(world, width, height);

        // Load ambient music
        ambientMusic = Gdx.audio.newMusic(Gdx.files.internal("music/ambient.mp3"));
        ambientMusic.setVolume(1f);
        ambientMusic.setLooping(true);
        ambientMusic.play();
    }

    /**
     * Renders the player and the walls.
     * Updates lights and box2d physics.
     * {@inheritDoc}
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Sets background to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen

        Vector2 pos = player.getPosition();

        camera.position.set(pos.x, pos.y, 0);
        camera.update();

        // Apply colors to player and wall objects
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(Color.DARK_GRAY);

        // Draw the walls
        Array<WallModel> obs = level.getObstacles();

        for (int i = 0; i < obs.size; i++) {
            WallModel wall = obs.get(i);
            Vector2 wallPos = wall.getPosition();
            shapes.rect(wallPos.x - wall.getWidth(), wallPos.y - wall.getHeight(), wall.getWidth() * 2, wall.getHeight() * 2f);
        }

        shapes.end();

        // Player lighting
        player.updateLightDistance();
        player.updateLightPosition();
        rayHandler.setCombinedMatrix(camera.combined);
        rayHandler.updateAndRender();

        // Render the box2d world
        //renderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);

        // Log the fps
        fpsLogger.log();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
    	super.show();
    	Gdx.graphics.setContinuousRendering(true);
    }

    /**
     * Pauses the music.
     * {@inheritDoc}
     */
    @Override
    public void pause() {
        ambientMusic.pause();
    }

    /**
     * Resumes the music.
     * {@inheritDoc}
     */
    @Override
    public void resume() {
        ambientMusic.play();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        world.dispose();
        //renderer.dispose();
        rayHandler.dispose();
        ambientMusic.dispose();
    }

}
