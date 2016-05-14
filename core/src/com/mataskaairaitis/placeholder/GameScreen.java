package com.mataskaairaitis.placeholder;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mataskaairaitis.placeholder.models.PlayerModel;

import java.util.Random;

/**
 * Created by mataskairaitis on 12/05/16.
 */
public class GameScreen implements Screen {

    OrthographicCamera camera;
    Box2DDebugRenderer renderer;
    FPSLogger fpsLogger;
    Body circleBody;
    PointLight playerLight;
    World world;
    RayHandler rayHandler;
    ShapeRenderer shapes;
    float width, height;

    PlayerModel player;
    float playerLightInterval;

    public GameScreen(final MyGdxGame game) {
        width = 1280;
        height = 720;

        player = new PlayerModel(width * 0.5f, height * 0.5f, 10f, Color.YELLOW);

        camera = new OrthographicCamera(width, height);
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        shapes = new ShapeRenderer();

        world = new World(new Vector2(0, 0), true);
        renderer = new Box2DDebugRenderer();
        fpsLogger = new FPSLogger();

        BodyDef circleDef = new BodyDef();
        circleDef.type = BodyDef.BodyType.DynamicBody;
        circleDef.position.set(player.getX(), player.getY());

        circleBody = world.createBody(circleDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(player.getRadius());

        FixtureDef circleFixture = new FixtureDef();
        circleFixture.shape = circleShape;
        circleFixture.density = 0.4f;
        circleFixture.friction = 0.2f;
        circleFixture.restitution = 0.8f;

        circleBody.createFixture(circleFixture);

        // Lighting settings
        rayHandler = new RayHandler(world);
        rayHandler.setCombinedMatrix(camera.combined);

        playerLight = new PointLight(rayHandler, 50000, Color.ORANGE, 100, player.getX(), player.getY());
        //playerLightInterval = 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Sets background to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen

        // Render the world
        renderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);

        // Color the circle
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(Color.YELLOW);
        shapes.circle(player.getX(), player.getY(), player.getRadius());
        shapes.end();

        // Box2dlight stuff
        playerLight.setDistance((float)Math.sin(playerLightInterval) * 10f + 200f);
        playerLightInterval  += 0.1f;
        rayHandler.updateAndRender();

        // Log the fps
        fpsLogger.log();
    }


    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        world.dispose();
    }

}
