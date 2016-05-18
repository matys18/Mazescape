package com.mataskaairaitis.placeholder.levels;

import com.badlogic.gdx.physics.box2d.World;
import com.mataskaairaitis.placeholder.models.LevelModel;

/**
 * Width is 1280, height 720
 * 
 * left boundary: 100
 * bottom boundary: 90
 * right: 1180
 * top: 630
 * 
 * 
 * Created by mataskairaitis on 17/05/16.
 */
public class Level1 extends LevelModel {
	
    public Level1(World world, float width, float height) {
        super(world, width, height, 10);

        // Add more walls here
        // obstacles.add(...

        vertWall(110, 90, 520);
        
        horiWall(100, 280, 620);
        horiWall(380, 700, 620);
        horiWall(800, 1180, 620);
        
        vertWall(1170, 400, 630);
        vertWall(1050, 300, 525);
        vertWall(1170, 90, 300);
        
        horiWall(1050, 1180, 410);
        horiWall(930, 1180, 210);
        horiWall(680, 1180, 100);
        horiWall(320, 580, 100);
        horiWall(100, 220, 100);
        
        vertWall(220, 200, 315);
        vertWall(220, 415, 520);
        vertWall(330, 295, 520);
        
        horiWall(220, 330, 305);
        horiWall(320, 580, 210);
        
        vertWall(450, 200, 360);
        vertWall(450, 460, 620);
        vertWall(570, 200, 360);
        vertWall(570, 460, 620);
        
        horiWall(680, 950, 410);
        
        vertWall(690, 200, 315);
        vertWall(690, 505, 620);
        
        horiWall(800, 950, 515);
        horiWall(690, 950, 305);
        horiWall(690, 830, 210);
        
    }

}
