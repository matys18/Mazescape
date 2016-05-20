package com.mataskaairaitis.mazescape.models;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Creates a lightable circle.
 * @author Matas Kairaitis
 * @version 2016-06-20
 */
public class LightableCircleModel extends CircleModel {

    PointLight light;
    PointLight ambientLight;
    Color color;
    float lightDistance;
    float ambientDistance;
    float lightInterval;

    /**
     * Creates a new lightable circle.
     * @param pos  Position vector
     * @param radius  Radius of the circle
     * @param world   World in which circle is staged
     * @param rayHandler  rayHandler that handles lighting
     * @param col  Color of the circle
     * @param lightDistance  Distance of the small light
     * @param ambientDistance  Distance of the big light
     */
    public LightableCircleModel(Vector2 pos, float radius, World world,
                                RayHandler rayHandler, Color col, float lightDistance, float ambientDistance) {
        super(pos, radius, world);

        this.color = col;
        this.lightDistance = lightDistance;
        this.ambientDistance = ambientDistance;

        light = new PointLight(rayHandler, 1000, color, lightDistance, pos.x, pos.y);
        light.setSoftnessLength(20);

        // Point Light for the player
        ambientLight = new PointLight(rayHandler, 1000, color, ambientDistance, pos.x, pos.y);
        ambientLight.setSoftnessLength(20);
    }

    /**
     * Updates the position of the light objects attached to the player
     * based on the position of the player.
     */
    public void updateLightPosition() {
        Vector2 pos = circleBody.getPosition();
        ambientLight.setPosition(pos.x, pos.y);
        light.setPosition(pos.x, pos.y);
    }

    /**
     * Updates the distance of the light objects attached to the player
     * based on the position of the player.
     */
    public void updateLightDistance() {
        lightInterval  += 0.1f;
        ambientLight.setDistance((float)Math.sin(lightInterval) * 10f + ambientDistance);
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
