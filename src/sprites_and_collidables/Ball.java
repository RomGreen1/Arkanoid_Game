package sprites_and_collidables;
import biuoop.DrawSurface;
import java.awt.Color;
import game.GameLevel;
import geometry_primitive.Line;
import geometry_primitive.Point;

/**
 * The type Ball.
 */
public class Ball implements Sprite {
    private final GameEnvironment environment;
    private Point center;
    /**
     * The radius of the ball.
     */
    private final double r;
    /**
     * The Velocity.
     */
    private final Velocity velocity;

    /**
     * Instantiates a new Ball.
     *
     * @param center      the center
     * @param r           the r
     * @param v           the v
     * @param environment the environment
     */
    public Ball(Point center, int r, Velocity v, GameEnvironment environment) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.velocity = v;
        this.environment = environment;
    }

    /**
     * Calculate trajectory line of the ball.
     *
     * @param ball the ball
     * @return the trajectory line of the ball
     */
    public Line trajectory(Ball ball) {
        Point start = ball.center;
        Point end = new Point(ball.center.getX() + ball.velocity.getDx(),
                ball.center.getY() + ball.velocity.getDy());
        return new Line(start, end);
    }
    /**
     * the method draw the ball on the draw surface.
     *
     * @param d the draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), (int) this.r);
        d.setColor(Color.BLACK);
        d.drawCircle((int) this.center.getX(),
                (int) this.center.getY(), (int) this.r);
    }
    /**
     * the method tell the ball that time passed, so it need to move.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Add to game.
     *
     * @param game the game we to add a sprite to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Move one step. the method checks collisions and accordingly move the ball
     */
    public void moveOneStep() {
        //get collision info for ball
        CollisionInfo collisionInfo =
                this.environment.getClosestCollision(this.trajectory(this));
        if (collisionInfo.getCollisionPoint() == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        //check if collision point is one of the rectangles corners
        if (collisionInfo.cornerCollision()) {
            collisionInfo.getCollisionObject().hit(this,
                    collisionInfo.getCollisionPoint(), this.velocity);
            return;
        } else if (collisionInfo.cornerOfSurface()) {
            //if collision point is at the corner of the draw surface
            collisionInfo.getCollisionObject().hit(this,
                    collisionInfo.getCollisionPoint(), this.velocity);
            return;
        }
        //if ball next move is the collision point, activate hit method
        collisionInfo.getCollisionObject().hit(this,
                    collisionInfo.getCollisionPoint(), this.velocity);
            //move to next point, using the velocity of the ball
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Remove from game.
     *
     * @param game the game we want to remove the sprite from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
