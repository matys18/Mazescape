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
 * Created by mataskairaitis on 15/05/16.
 */
public class WallModel {

    float width;
    float height;
    Body body;
    PointLight playerLight;

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

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}