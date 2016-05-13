package com.mataskaairaitis.placeholder;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;

/**
 * Created by mataskairaitis on 12/05/16.
 */
public class GameScreen extends ParentScreen {

    OrthographicCamera camera;
    Box2DDebugRenderer renderer;
    FPSLogger fpsLogger;
    Body circleBody;
    PointLight playerLight;
    World world;
    RayHandler rayHandler;

    public GameScreen(final MyGdxGame game) {
    	super(game, GameControl.class);

        camera = new OrthographicCamera(width, height);
        camera.position.set(width * 0.5f, height * 0.5f, 0);
        camera.update();

        world = new World(new Vector2(0, 0), true);
        renderer = new Box2DDebugRenderer();
        fpsLogger = new FPSLogger();

        BodyDef circleDef = new BodyDef();
        circleDef.type = BodyDef.BodyType.DynamicBody;
        circleDef.position.set(width * 0.5f, height * 0.5f);

        circleBody = world.createBody(circleDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(6f);

        FixtureDef circleFixture = new FixtureDef();
        circleFixture.shape = circleShape;
        circleFixture.density = 0.4f;
        circleFixture.friction = 0.2f;
        circleFixture.restitution = 0.8f;

        circleBody.createFixture(circleFixture);

        // Lighting settings
        rayHandler = new RayHandler(world);
        rayHandler.setCombinedMatrix(camera.combined);

        playerLight = new PointLight(rayHandler, 50000, Color.ORANGE, 100, width / 2, height / 2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Sets background to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen

        // Render the world
        renderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);

        // Log the fps
        fpsLogger.log();

        // Box2dlight stuff
        playerLight.setDistance(new Random().nextInt(200));
        rayHandler.updateAndRender();
    }

    @Override
    public void show() {
    	super.show();
    	Gdx.graphics.setContinuousRendering(true);
    }

    @Override
    public void dispose() {
        world.dispose();
    }

}
