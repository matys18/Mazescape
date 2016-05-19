package com.mataskaairaitis.placeholder.models;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The model that represents a wall in the game.
 * @author Matas Kairaitis
 * @version 2016-06-19
 */
public class WallModel {

    float width;
    float height;
    Body body;
    PointLight playerLight;

    /**
     * Constructs a new WallModel with the following properties.
     * @param x  X-coordinate of the wall
     * @param y  Y-coordinate of the wall
     * @param width  Width of the wall
     * @param height  Height of the wall
     * @param world  World in which the wall is staged
     */
    public WallModel(float x, float y, float width, float height, World world) {

        this.width = width;
        this.height = height;

        // Create a body definition for the character
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(width, height);

        body.createFixture(bodyShape, 0f);
    }

    /**
     * Getter for the position of the wall.
     * @return  x,y coordinates of the wall
     */
    public Vector2 getPosition() {
        return body.getPosition();
    }

    /**
     * Getter for the width of the wall.
     * @return Width of the wall
     */
    public float getWidth() {
        return width;
    }

    /**
     * Getter for the height of the wall
     * @return  Height of the wall
     */
    public float getHeight() {
        return height;
    }

}