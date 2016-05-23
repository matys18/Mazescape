package com.mataskaairaitis.mazescape.levels;

import com.badlogic.gdx.physics.box2d.World;
import com.mataskaairaitis.mazescape.models.LevelModel;

/**
 * Represents the first level of the misc.
 * Builds the maze by constructing all the walls.
 * 
 * @author Jarl Silven, Matas Kairaitis
 * @version 2016-05-20
 */
public class Level1 extends LevelModel {

    /**
     * {@inheritDoc}
     */
    public Level1(World world, float width, float height) {
        super(world, width, height, 10);

        // leftmost "real" wall: 100, rightmost "real" wall: 1180
        // Topmost "real wall: 630, bottommost "real" wall: 90
  
        // Walls are grouped by closeness and type
        // v for vertical, h for horizontal, o for outside (of "real" maze)
        vertWall(110, 90, 315);		// v1
        vertWall(220, 200, 315);	// v2
        vertWall(110, 400, 630);	// v3
        vertWall(220, 400, 520);	// v4
        vertWall(330, 295, 520);	// v5
        
        vertWall(-10, 90, 315);		// ov1
        vertWall(-10, 400, 630);	// ov3
        
        vertWall(450, 460, 620);	// v6
        vertWall(570, 460, 620);	// v7
        vertWall(690, 505, 620);	// v8
        vertWall(450, 100, 360);	// v9
        vertWall(570, 200, 360);	// v10
        vertWall(690, 200, 315);	// v11
        
        vertWall(940, 410, 515);	// v12
        vertWall(1050, 300, 525);	// v13
        vertWall(1170, 400, 630);	// v14
        vertWall(1170, 90, 315);	// v15
        
        vertWall(1290, 400, 630);	// ov14
        vertWall(1290, 90, 315);	// ov15
        
        horiWall(100, 220, 100);	// h1
        horiWall(220, 330, 305);	// h2
        horiWall(320, 580, 210);	// h3
        horiWall(320, 700, 100);	// h4
        
        horiWall(100, 220, 740);	// oh1
        horiWall(320, 700, 740);	// oh4
        
        horiWall(100, 340, 620);	// h5
        horiWall(440, 700, 620);	// h6

        horiWall(100, 340, -20);	// oh5
        horiWall(440, 700, -20);	// oh6
        
        horiWall(800, 1180, 620);	// h7
        horiWall(800, 950, 515);	// h8
        horiWall(680, 950, 410);	// h9
        horiWall(1050, 1180, 410);	// h10
        
        horiWall(800, 1180, -20);	// oh7
        
        horiWall(690, 950, 305);	// h11
        horiWall(690, 830, 210);	// h12
        horiWall(930, 1180, 210);	// h13
        horiWall(800, 950, 100); 	// h14
        horiWall(1050, 1180, 100);	// h15

        horiWall(800, 950, 740); 	// oh14
        horiWall(1050, 1180, 740);	// oh15
        
        horiWall(-100, 100, 410);	// o1
        horiWall(1180, 1380, 410);	// o1
        vertWall(330, -100, 100);	// o2
        vertWall(330, 620, 900);	// o2
        vertWall(810, -100, 100);	// o3
        vertWall(810, 620, 900);	// o3
    }

}
