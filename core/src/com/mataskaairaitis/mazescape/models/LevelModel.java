package com.mataskaairaitis.mazescape.models;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * The model that represents a Level in the misc.
 * @author Matas Kairaitis, Jarl Silven
 * @version 2016-06-19
 */
public class LevelModel {

    World world;
    float width;
    float height;
    float thickness;
    private Array<WallModel> obstacles;

    /**
     * Constructs a new Level with the following properties
     * and creates obstacles staged in the level.
     * @param world  The world in which the level is staged
     * @param width  The width of the level
     * @param height  The height of the level
     * @param thickness  The thickness of an obstacle in the level
     */
    public LevelModel(World world, float width, float height, float thickness) {
        this.world = world;
        this.width = width;
        this.height = height;
        this.thickness = thickness;

        obstacles = new Array<WallModel>();
    }
    
    /**
     * Create a horizontal wall in the Level.
     * @param x1  Left X-coordinate
     * @param x2  Right X-coordinate
     * @param y   Y-coordinate of the wall
     */
    public void horiWall(float x1, float x2, float y) {
    	obstacles.add(new WallModel((x1 + x2)/2, y, (x2 - x1)/2, thickness, world));
    }
    
    /**
     * Make a vertical wall somewhere on the level.
     * @param x  X-coordinate of the wall
     * @param y1 Lower Y-coordinate
     * @param y2 Upper Y-coordinate
     */
    public void vertWall(float x, float y1, float y2) {
    	obstacles.add(new WallModel(x, (y1 + y2)/2, thickness, (y2-y1)/2, world));
    }

    /**
     * Getter for the obstacles in this level.
     * @return  Array of obstacles in this level
     */
    public Array<WallModel> getObstacles() {
        return obstacles;
    }

}
