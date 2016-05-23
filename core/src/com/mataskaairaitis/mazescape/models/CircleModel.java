package com.mataskaairaitis.mazescape.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Model representing a box2d circle.
 * @author Matas Kairaitis
 * @version 2016-06-20
 */
public class CircleModel {

    Body circleBody;

    /**
     * Creates a new circle with physical box2d properties.
     * @param pos  Position of the circle
     * @param radius  Radius of the circle
     * @param world   World in which the circle is staged
     */
    public CircleModel(Vector2 pos, float radius, World world) {

        // Create a body definition for the circle
        BodyDef circleDef = new BodyDef();
        circleDef.type = BodyDef.BodyType.DynamicBody;
        circleDef.position.set(pos.x, pos.y);

        // Create a box2d body
        circleBody = world.createBody(circleDef);

        // Set the shape
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        // Create a fixture with physical properties
        FixtureDef circleFixture = new FixtureDef();
        circleFixture.shape = circleShape;
        circleFixture.density = 0f;
        circleFixture.friction = 0f;
        circleFixture.restitution = 0.05f;

        // Attach the fixture to the player body
        circleBody.createFixture(circleFixture);
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
     * Teleports the player to new position
     * @param v  New x,y position of the player
     */
    public void setPosition(Vector2 v) {
    	circleBody.setTransform(v, 0);
    }

    /**
     * Getter for the position of the player.
     * @return  Current x,y positions of the player
     */
    public Vector2 getPosition() {
        return circleBody.getPosition();
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

}
