package com.mataskaairaitis.placeholder.models;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by mataskairaitis on 12/05/16.
 */
public class PlayerModel {

    Body circleBody;
    PointLight playerLight;
    PointLight ambientPlayerLight;
    float playerLightInterval;
    Color color;

    public PlayerModel(float x, float y, float radius, World world, RayHandler rayHandler, Color col) {
        // Set the player color
        color = col;

        // Create a body definition for the character
        BodyDef circleDef = new BodyDef();
        circleDef.type = BodyDef.BodyType.DynamicBody;
        circleDef.position.set(x, y);

        // Create a box2d body from the circle def
        circleBody = world.createBody(circleDef);

        // Set the shape of our player body
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        // Create a fixture with physical properties for player body
        FixtureDef circleFixture = new FixtureDef();
        circleFixture.shape = circleShape;
        circleFixture.density = 0f;
        circleFixture.friction = 0f;
        circleFixture.restitution = 0.05f;

        // Attach the fixture to the player body
        circleBody.createFixture(circleFixture);

        playerLight = new PointLight(rayHandler, 5000, color, 20f, x, y);
        playerLight.setSoftnessLength(20);

        // Point Light for the player
        ambientPlayerLight = new PointLight(rayHandler, 5000, color, 150f, x, y);
        ambientPlayerLight.setSoftnessLength(20);
    }

    public void setVelocity(Vector2 v) {
        circleBody.setLinearVelocity(v.x, v.y);
    }
    
    public Vector2 getVelocity() {
    	return circleBody.getLinearVelocity();
    }

    public Vector2 getPosition() {
        return circleBody.getPosition();
    }

    public void updateLightPosition() {
        Vector2 pos = circleBody.getPosition();
        ambientPlayerLight.setPosition(pos.x, pos.y);
        playerLight.setPosition(pos.x, pos.y);
    }

    public void updateLightDistance() {
        playerLightInterval  += 0.1f;
        ambientPlayerLight.setDistance((float)Math.sin(playerLightInterval) * 10f + 150f);
    }

    public float getRadius() {
        return circleBody.getFixtureList().get(0).getShape().getRadius();
    }

    public void setRadius(float radius) {
        circleBody.getFixtureList().get(0).getShape().setRadius(radius);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
