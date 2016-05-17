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
    private Array<WallModel> obstacles;

    public LevelModel(World world, float width, float height) {
        this.world = world;
        this.width = width;
        this.height = height;

        obstacles = new Array<WallModel>();

        // Generate bounding walls
        obstacles.add(new WallModel(10f, 0f, 10f, height, world));
        obstacles.add(new WallModel(width - 10f, 0f, 10f, height, world));
        obstacles.add(new WallModel(0f, 0f, width, 10f, world));
        obstacles.add(new WallModel(0f, height - 10f, width, 10f, world));
//        makeWall(width/2, height/3, 100, 10);
    }
    
    public void makeWall(float x, float y, float width, float height) {
    	obstacles.add(new WallModel(x, y, width, height, world));
    }

    public Array<WallModel> getObstacles() {
        return obstacles;
    }

}
