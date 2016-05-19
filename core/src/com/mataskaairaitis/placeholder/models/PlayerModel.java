package com.mataskaairaitis.placeholder.models;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * The model that represents the player in the game.
 * @author Matas Kairaitis
 * @version 2016-06-19
 */
public class PlayerModel {

    Body circleBody;
    PointLight playerLight;
    PointLight ambientPlayerLight;
    float playerLightInterval;
    Color color;

    /**
     * Creates a new player with the given properties.
     * @param x  The x coordinate of the player
     * @param y  The y coordinate of the player
     * @param radius  The radius of the player
     * @param world  The World in which the player is staged
     * @param rayHandler  The RayHandler associated with this player
     * @param col  The color of the player
     */
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

    /**
     * Setter for the velocity of the player.
     * @param v Magnitudes of the new x,y velocities of the player
     */
    public void setVelocity(Vector2 v) {
        circleBody.setLinearVelocity(v.x, v.y);
    }

    /**
     * Getter for the velocity of the player.
     * @return  Current x,y velocities of the player
     */
    public Vector2 getVelocity() {
    	return circleBody.getLinearVelocity();
    }

    /**
     * Getter for the position of the player.
     * @return  Current x,y positions of the player
     */
    public Vector2 getPosition() {
        return circleBody.getPosition();
    }

    /**
     * Updates the position of the light objects attached to the player
     * based on the position of the player.
     */
    public void updateLightPosition() {
        Vector2 pos = circleBody.getPosition();
        ambientPlayerLight.setPosition(pos.x, pos.y);
        playerLight.setPosition(pos.x, pos.y);
    }

    /**
     * Updates the distance of the light objects attached to the player
     * based on the position of the player.
     */
    public void updateLightDistance() {
        playerLightInterval  += 0.1f;
        ambientPlayerLight.setDistance((float)Math.sin(playerLightInterval) * 10f + 150f);
    }

    /**
     * Getter for the radius of the player.
     * @return The radius of the player
     */
    public float getRadius() {
        return circleBody.getFixtureList().get(0).getShape().getRadius();
    }

    /**
     * Setter for the radius of the player.
     * @param radius  The new radius of the player
     */
    public void setRadius(float radius) {
        circleBody.getFixtureList().get(0).getShape().setRadius(radius);
    }

    /**
     * Getter for the color of the player.
     * @return  Color of the player.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter for the color of the player.
     * @param color  New color of the player.
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
