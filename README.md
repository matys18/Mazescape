### Mazescape

A 2d maze game where you try to escape the dark.

![Teaser image](teaser.png)

##### How to run the game

* Download the executable [here](https://github.com/matys18/Mazescape/releases).
* Use arrow keys to move around and enter key to select menu items.

##### How to build the project from source

The project is written in Java and uses the Gradle build system.

1. Clone the repo
2. Cd into ``/desktop``
3. ``gradlew build``
4. Put on headphones
5. ``gradlew run``

##### MVP

* A maze game with walls and collisions
* Player-controlled character movable by keyboards inputs
* Distance-based vision system.
* A time-based difficulty system based on how fast the level was completed.

##### Target-platform

Cross-platform Desktop Machines.

* Windows
* Mac
* Linux

For more information see the [libgdx homepage](https://libgdx.badlogicgames.com/features.html).

##### Dependencies

* [Libgdx](https://libgdx.badlogicgames.com/index.html)
    * box2d
    * box2dlights
    * Freetype
