package sprites_and_collidables;

import geometry_primitive.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    public static final int X_WIDTH_BORDER = 800;
    public static final int Y_LIMIT_BORDER = 600;
    public static final int X_START = 0;
    public static final int Y_START = 0;
    private final Point collisionPoint;
    private final Collidable collisionObject;
    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Gets collision point of the collision info.
     *
     * @return the collision point
     */
    public Point getCollisionPoint() {
        return collisionPoint;
    }

    /**
     * Gets collision object of the collision info.
     *
     * @return the collision object
     */
    public Collidable getCollisionObject() {
        return this.collisionObject;
    }
    /**
     * check if collision point is in one of the corners of the draw surface.
     *
     * @return true if the collision point is in one of the corners of the draw
     * surface, false otherwise.
     */
    public boolean cornerOfSurface() {
        //get the corners of the surface
        Point upperLeft = new Point(X_START, Y_START);
        Point upperRight = new Point(X_WIDTH_BORDER, Y_START);
        Point lowerLeft = new Point(X_START, Y_LIMIT_BORDER);
        Point lowerRight = new Point(X_WIDTH_BORDER, Y_LIMIT_BORDER);
        //check if collision is in one of the points, return the answer
        return this.collisionPoint.equals(upperLeft)
                || this.collisionPoint.equals(upperRight)
                || this.collisionPoint.equals(lowerLeft)
                || this.collisionPoint.equals(lowerRight);
    }

    /**
     * check if collision point is in one of the collision object corners.
     *
     @return true if the collision point is in one of the corners of the
     collision object, false otherwise.
     */
    public boolean cornerCollision() {
        //get the corners of the collision object
        Point upperLeft =
                this.collisionObject.getCollisionRectangle().getUpperLeft();
        Point upperRight =
                new Point(this.collisionObject.getCollisionRectangle()
                        .getUpperLeft().getX()
                        + this.collisionObject.getCollisionRectangle()
                        .getWidth(), this.collisionObject.
                        getCollisionRectangle().getUpperLeft().getY());
        Point lowerLeft = new Point(this.collisionObject.
                getCollisionRectangle().getUpperLeft().getX(),
                this.collisionObject.getCollisionRectangle()
                        .getUpperLeft().getY()
                        + this.collisionObject.getCollisionRectangle()
                        .getHeight());
        Point lowerRight = new Point(this.collisionObject.
                getCollisionRectangle().getUpperLeft().getX()
                + this.collisionObject.getCollisionRectangle().getWidth(),
                this.collisionObject.getCollisionRectangle().
                        getUpperLeft().getY()
                + this.collisionObject.getCollisionRectangle().getHeight());
        //check if collision is in one of the points, return the answer
        return this.collisionPoint.equals(upperLeft)
                || this.collisionPoint.equals(upperRight)
                || this.collisionPoint.equals(lowerLeft)
                || this.collisionPoint.equals(lowerRight);
    }
}
