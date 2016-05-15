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
    float playerLightInterval;
    Color color;

    public PlayerModel(float x, float y, float radius, World world, RayHandler rayHandler, Color color) {
        // Set the player color
        this.color = color;

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
        circleFixture.density = 0.4f;
        circleFixture.friction = 0f;
        circleFixture.restitution = 0.1f;

        // Attach the fixture to the player body
        circleBody.createFixture(circleFixture);

        // Point Light for the player
        playerLight = new PointLight(rayHandler, 50000, Color.ORANGE, 100, x, y);
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
        playerLight.setPosition(pos.x, pos.y);
    }

    public void updateLightDistance() {
        playerLightInterval  += 0.1f;
        playerLight.setDistance((float)Math.sin(playerLightInterval) * 5f + 100f);
    }

    /*
    public float getRadius() {
        return circleBody.
    }

    public void setRadius(float radius) {
        this.radius = radius;
    } */

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
