package com.mataskaairaitis.mazescape.models;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * The model that represents the player in the misc.
 * @author Matas Kairaitis
 * @version 2016-06-19
 */
public class PlayerModel extends LightableCircleModel {

    /**
     * Creates a new player with the given properties.
     * @param x  The x coordinate of the player
     * @param y  The y coordinate of the player
     * @param radius  The radius of the player
     * @param world  The World in which the player is staged
     * @param rayHandler  The RayHandler associated with this player
     * @param col  The color of the player
     */
    public PlayerModel(Vector2 pos, float radius, World world, RayHandler rayHandler, Color col) {
        super(pos, radius, world, rayHandler, col, 20, 150);
    }

}
