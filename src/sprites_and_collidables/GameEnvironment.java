package sprites_and_collidables;
import geometry_primitive.Point;
import geometry_primitive.Line;
import java.util.ArrayList;

/**
 * The type Game environment.
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidables;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }
    /**
     * Add colllidable to game environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        //add the rectangle to the list of collidable objects
        collidables.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable we want to remove
     */
    public void removeCollidable(Collidable c) {
        //remove the collidable from the list of collidable objects
        collidables.remove(c);
    }

    /**
     * Gets the closest collision of the trajectory line of the ball with the
     * collidables in the game.
     *
     * @param trajectory the trajectory line of the ball
     * @return the closest collision to the ball movement line,
     * and in which object it is going to collide
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestClosestPoint = null;
        Collidable collidableObject = null;
        for (Collidable collidable : collidables) {
            //for every rectangle, check the closest collision point
            Point closestIntersection =
                    trajectory.
                            closestIntersectionToStartOfLine(
                                    collidable
                                            .getCollisionRectangle());
            /*
            between the closest collision point of each rectangle, we need
            the closest collision point from them
             */
            if (closestIntersection != null) {
                if (closestClosestPoint == null) {
                    closestClosestPoint = closestIntersection;
                    collidableObject = collidable;
                } else if (trajectory.start().distance(closestIntersection)
                        < trajectory.start().distance(closestClosestPoint)) {
                    /*
                    in case that closest intersection is closer to line.start
                    than closestClosestPoint, make closestClosestPoint the
                    new closestIntersection
                     */
                    closestClosestPoint = closestIntersection;
                    collidableObject = collidable;
                }
            }
        }
        return new CollisionInfo(closestClosestPoint, collidableObject);
    }
}
