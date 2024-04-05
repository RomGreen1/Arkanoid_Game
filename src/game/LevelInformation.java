package game;

import biuoop.DrawSurface;
import geometry_primitive.Point;
import sprites_and_collidables.Block;
import sprites_and_collidables.Sprite;
import sprites_and_collidables.Velocity;

import java.awt.Color;
import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Gets color.
     *
     * @return the color for the background of the level
     */
    Color getColor();

    /**
     * Number of balls in the level.
     *
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return list that contains the velocities of the ball that will take part
     * in the level
     */
    List<Velocity> initialBallVelocities();

    /**
     * determine the paddle speed.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * determine the paddle width.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Level name.
     *
     * @return the level name that will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * Gets background of the level.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * Block list for the level.
     *
     * @return the list of block that will take part in the level
     */
    List<Block> blocks();

    /**
     * to pass from level to level, we need to know how many blocks we need
     * to hit.
     *
     * @return the number of blocks to remove until the level is "cleared".
     */
    int numberOfBlocksToRemove();

    /**
     * in this method we draw the background for the level.
     *
     * @param d the draw surface we draw on
     */
    void drawBackground(DrawSurface d);

    /**
     * in this method we create ball points list.
     *
     * @return the points list
     */
    List<Point> ballPoints();
}