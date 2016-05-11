package com.mataskaairaitis.placeholder;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;

/**
 * Testgame class.
 * 
 * VERSION 1:
 * Extending the testgame with some more stuff:
 * 1: Sprite, turning the samplepicture texture into a movable sprite
 * 2: Input, moving the sample picture around the window
 * 3: setting the position to the middle of window at start.
 * TODO: organize input better in a separate class, with better
 * logic. Doing an if-statement for each key seems suboptimal.
 * VERSION 2:
 * All input is now handled by UserInput class, which also uses
 * reflection to direct input to methods instead of if-statements.
 */
public class MyGdxGame extends ApplicationAdapter {
	OrthographicCamera camera;
	Box2DDebugRenderer renderer;
	FPSLogger fpsLogger;
	Body circleBody;
	PointLight kek;

	float width, height;

	World world;
	RayHandler rayHandler;

	Circle player;
	
	@Override
	public void create () {
		width = 1280;
		height = 720;

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

		kek = new PointLight(rayHandler, 50000, Color.ORANGE, 100, width / 2 - 50, height / 2 + 15);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); // Sets background to black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen

		renderer.render(world, camera.combined);
		world.step(1/60f, 6, 2);

		fpsLogger.log();

		kek.setDistance(new Random().nextInt(200));
		rayHandler.updateAndRender();
	}

	@Override
	public void dispose() {
		world.dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void resize(int width, int height) {

	}
}
