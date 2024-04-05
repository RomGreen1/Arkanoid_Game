package sprites_and_collidables;

import geometry_primitive.Point;
import geometry_primitive.Rectangle;

/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param ball the ball that makes the hit
     * @return the velocity
     */
    Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity);
}
