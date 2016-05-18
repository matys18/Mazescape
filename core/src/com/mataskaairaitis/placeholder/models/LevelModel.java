package com.mataskaairaitis.placeholder.models;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mataskaairaitis.placeholder.models.WallModel;

/**
 * Created by mataskairaitis on 17/05/16.
 */
public class LevelModel {

    World world;
    float width;
    float height;
    protected float thickness;
    private Array<WallModel> obstacles;

    public LevelModel(World world, float width, float height, float thickness) {
        this.world = world;
        this.width = width;
        this.height = height;
        this.thickness = thickness;

        obstacles = new Array<WallModel>();

        // Generate bounding walls
        obstacles.add(new WallModel(10f, 0f, 10f, height, world));
        obstacles.add(new WallModel(width - 10f, 0f, 10f, height, world));
        obstacles.add(new WallModel(0f, 0f, width, 10f, world));
        obstacles.add(new WallModel(0f, height - 10f, width, 10f, world));
    }
    
    /**
     * Make a horizontal wall somewhere on the Level.
     * @param x1 left x coordinate
     * @param x2 right x coordinate
     * @param y
     */
    public void horiWall(float x1, float x2, float y) {
    	obstacles.add(new WallModel((x1 + x2)/2, y, (x2 - x1)/2, thickness, world));
    }
    
    /**
     * Make a vertical wall somewhere on the level.
     * @param x
     * @param y1 lower y coordinate
     * @param y2 upper y coordinate
     */
    public void vertWall(float x, float y1, float y2) {
    	obstacles.add(new WallModel(x, (y1 + y2)/2, thickness, (y2-y1)/2, world));
    }

    public Array<WallModel> getObstacles() {
        return obstacles;
    }

}
