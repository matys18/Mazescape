package com.mataskaairaitis.placeholder.levels;

import com.badlogic.gdx.physics.box2d.World;
import com.mataskaairaitis.placeholder.models.LevelModel;

/**
 * Width is 1280, height 720
 * middle is 640, and 360.
 * 
 * Created by mataskairaitis on 17/05/16.
 */
public class Level1 extends LevelModel {
	
	int th = 10; // th for thickness

    public Level1(World world, float width, float height) {
        super(world, width, height);

        // Add more walls here
        // obstacles.add(...
        
        makeWall(640, 300, 100, th);
        
    }

}
