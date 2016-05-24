package com.mataskaairaitis.mazescape.screens;

import java.util.HashMap;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.mataskaairaitis.mazescape.Mazescape;
import com.mataskaairaitis.mazescape.input.*;
import com.mataskaairaitis.mazescape.levels.*;
import com.mataskaairaitis.mazescape.models.*;
import com.mataskaairaitis.mazescape.misc.*;

/**
 * The Screen that represents the actual misc.
 * @author Matas Kairaitis
 * @version 2016-05-19
 */
public class GameScreen extends ParentScreen {

    OrthographicCamera camera;
    Box2DDebugRenderer renderer;
    FPSLogger fpsLogger;
    World world;
    RayHandler rayHandler;
    ShapeRenderer shapes;
    Music ambientMusic;

    PlayerModel player;
    CircleModel goal;
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
        renderer = new Box2DDebugRenderer();
        fpsLogger = new FPSLogger();
        
        // Create the box2d world
        world = new World(new Vector2(0, 0), true);

        // Lighting settings
        rayHandler = new RayHandler(world);
        rayHandler.setCombinedMatrix(camera.combined);

        // Create a player instance
        player = new PlayerModel(new Vector2(510f, 270f), 6f, world, rayHandler, Color.ORANGE);

        // Creates the goal
        goal = new CircleModel(new Vector2(1110f, 458f), 5f, world);

        // Load obsticles for this level
        level = new Level1(world, width, height);

        // Load ambient music
        ambientMusic = Gdx.audio.newMusic(Gdx.files.internal("music/ambient.mp3"));
        ambientMusic.setVolume(1f);
        ambientMusic.setLooping(true);
        ambientMusic.play();

        // Creates collision listener
        world.setContactListener(new CollisionDetector(this));
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
        
        if(pos.x < 50)
        	player.setPosition(new Vector2(1230, pos.y));
        else if(pos.x > 1230)
        	player.setPosition(new Vector2(50, pos.y));
        if(pos.y < 40)
        	player.setPosition(new Vector2(pos.x, 680));
        else if(pos.y > 680)
        	player.setPosition(new Vector2(pos.x, 40));

        camera.position.set(pos.x, pos.y, 0);
        camera.update();

        // Apply colors to player and wall objects
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(Color.DARK_GRAY);

        // Draw the walls
        for (WallModel wall : level.getObstacles()) {
            shapes.rect(wall.getPosition().x - wall.getWidth(), 
            		wall.getPosition().y - wall.getHeight(), 
            		wall.getWidth() * 2, 
            		wall.getHeight() * 2f);
        }

        // Draw the goal
        shapes.setColor(Color.WHITE);
        Vector2 goalPos = goal.getPosition();
        shapes.circle(goalPos.x, goalPos.y, goal.getRadius(), 1000);

        shapes.end();

        // Player lighting
        player.updateLightDistance();
        player.updateLightPosition();
        rayHandler.setCombinedMatrix(camera.combined);
        rayHandler.updateAndRender();

        // Render the box2d world
//        renderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);

        // Log the fps
        fpsLogger.log();
    }

    /**
     * Displays the EndScreen with win boolean true.
     */
    public void win() {
    	game.setEndScreen(new EndScreen(getGame(), true));
    	game.setScreen(game.getEndScreen());
    }

    /**
     * Displays the EndScreen with win boolean false.
     */
    public void loose() {
    	game.setEndScreen(new EndScreen(getGame(), false));
    	game.setScreen(game.getEndScreen());
    }

    /**
     * Getter for the player.
     * @return Model representing the player
     */
    public PlayerModel getPlayer() { return player; }

    /**
     * Getter for the goal.
     * @return Model representing the goal
     */
    public CircleModel getGoal() { return goal; }

    /**
	 * {@inheritDoc}
	 */
	@Override
	public void hide() {
		super.hide();
		pause();
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
    	super.show();
    	resume();
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
