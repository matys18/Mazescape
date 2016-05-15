package com.mataskaairaitis.placeholder;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mataskaairaitis.placeholder.models.PlayerModel;
import com.mataskaairaitis.placeholder.models.WallModel;

/**
 * Created by mataskairaitis on 12/05/16.
 */
public class GameScreen extends ParentScreen {

    OrthographicCamera camera;
    Box2DDebugRenderer renderer;
    FPSLogger fpsLogger;
    World world;
    RayHandler rayHandler;
    ShapeRenderer shapes;

    PlayerModel player;
    WallModel wall;

    float playerLightInterval;

    public GameScreen(final Mazescape game) {
    	super(game, GameControl.class);

        // Create the camera and set it's position
        camera = new OrthographicCamera(width, height);
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
        player = new PlayerModel(width * 0.5f, height * 0.5f, 6f, world, rayHandler, Color.ORANGE);

        wall = new WallModel(20f, height * 0.5f, 10f, 100f, world, rayHandler);

        //player.setVelocity(new Vector2(-60, 0));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Sets background to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen

        Vector2 pos = player.getPosition();

        Vector2 wallPos = wall.getPosition();

        // Render the box2d world
        renderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);

        //camera.position.set(pos.x , pos.y, 0);
        //camera.update();

        // Apply colors to player and wall objects
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(player.getColor());
        shapes.circle(pos.x /3f, pos.y /3f, player.getRadius());
        shapes.setColor(Color.DARK_GRAY);
        shapes.rect(wallPos.x - wall.getWidth(), wallPos.y - wall.getHeight(), wall.getWidth() * 2, wall.getHeight() * 2f);
        shapes.end();

        // Player lighting
        player.updateLightDistance();
        player.updateLightPosition();
        rayHandler.updateAndRender();

        // Log the fps
        fpsLogger.log();
    }

    @Override
    public void show() {
    	super.show();
    	Gdx.graphics.setContinuousRendering(true);
    }

    @Override
    public void dispose() {
        world.dispose();
        renderer.dispose();
        rayHandler.dispose();
    }

}
