package sprites_and_collidables;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry_primitive.Point;
import geometry_primitive.Rectangle;
import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private static final int ZONE_ONE = 1;
    private static final int ZONE_TWO = 2;
    private static final int ZONE_THREE = 3;
    private static final int ZONE_FOUR = 4;
    private static final int ZONE_FIVE = 5;
    public static final int MOVE_PADDLE = 5;
    public static final int DEFAULT_SPEED = 4;
    public static final double LEFT_LIMIT_X = 30;
    public static final double RIGHT_LIMIT_X = 770;
    private final int speed;

    private Point upperLeft;
    private final double width;
    private final double height;
    private final KeyboardSensor keyboard;

    /**
     * Instantiates a new Paddle.
     *
     * @param upperLeft      the upper left of the paddle block
     * @param width          the width of the paddle block
     * @param height         the height of the paddle block
     * @param keyboardSensor the keyboard sensor of the paddle block
     * @param paddleSpeed    the paddle speed
     */
    public Paddle(Point upperLeft, double width, double height,
                  KeyboardSensor keyboardSensor, int paddleSpeed) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        this.keyboard = keyboardSensor;
        this.speed = paddleSpeed;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
      if (this.upperLeft.getX() < LEFT_LIMIT_X) {
          return;
      }
      this.upperLeft = new Point(this.upperLeft.getX() - this.speed,
              this.upperLeft.getY());
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (this.upperLeft.getX() + this.width > RIGHT_LIMIT_X) {
            return;
        }
        this.upperLeft = new Point(this.upperLeft.getX() + this.speed,
                this.upperLeft.getY());
    }
    /**
     * tell paddle if time has passed. the method check if the user pressed
     * right or left key and make the paddle move accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
    }
    /**
     * draw the paddle on the surface.
     *
     * @param d the draw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) upperLeft.getX(),
                (int) upperLeft.getY(),
                (int) width, (int) height);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) upperLeft.getX(),
                (int) upperLeft.getY(),
                (int) width, (int) height);
    }

    /**
     * gives the rectangle shape of the paddle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.upperLeft, this.width,
                this.height);
    }

    /**
     * check if the collision is vertical type or not.
     *
     * @param paddle the paddle that take part in the collision
     * @param collisionPoint the collision point
     * @return true if collision is vertical, false otherwise
     */
    public boolean verCollision(Paddle paddle, Point collisionPoint) {
        /*
        check if y value of collision point is equal to y value of
        one of the horizontal lines in the rectangle. if so, the collision
        is vertical type.
         */
        return collisionPoint.getY() == paddle.upperLeft.getY()
                || collisionPoint.getY()
                == paddle.upperLeft.getY() + paddle.height;
    }

    /**
     * Check hit zone of the paddle and ball.
     *
     * @param collisionPoint the collision point of paddle and ball
     * @return the zone in which the paddle got hit
     */
    public int checkHit(Point collisionPoint) {
        double hitRange = collisionPoint.getX() - this.upperLeft.getX();
        //check in which fifth the paddle got hit
        if (hitRange <= (this.width / MOVE_PADDLE)) {
            return ZONE_ONE;
        } else if (hitRange <= (this.width / MOVE_PADDLE) * 2) {
            return ZONE_TWO;
        } else if (hitRange <= (this.width / MOVE_PADDLE) * 3) {
            return ZONE_THREE;
        } else if (hitRange <= (this.width / MOVE_PADDLE) * 4) {
            return ZONE_FOUR;
        } else {
            return ZONE_FIVE;
        }
    }
    /**
     * handle hit by changing velocity of the ball according to the collision
     * point and paddle.
     *
     * @param collisionPoint collision point of the ball with the block
     * @param currentVelocity current velocity of the ball
     * @param ball the hitter
     * @return updated velocity of ball
     */
    public Velocity hit(Ball ball, Point collisionPoint,
                        Velocity currentVelocity) {
        //check hit zone
        int hitZone = checkHit(collisionPoint);
        //check if collision is vertical type
        if (verCollision(this, collisionPoint)) {
            /*
            check if collision is in lower line of paddle, if so, the ball
            missed the paddle and the ball velocity does not need to change
             */
            if (collisionPoint.getY() == this.upperLeft.getY() + this.height) {
               return currentVelocity;
            }
            //if yes, handle angle hit
            currentVelocity = angleHit(hitZone, currentVelocity);
        } else {
            //if collision is horizontal, change dx sign
            currentVelocity.setDx(-(currentVelocity.getDx()));
        }
        return currentVelocity;
    }

    /**
     * According to the hit zone of the paddle, the method gives the ball the
     * suitable velocity.
     *
     * @param hitZone  the hit zone of the paddle
     * @param velocity the velocity of the ball
     * @return the new ball velocity
     */
    public Velocity angleHit(int hitZone, Velocity velocity) {
        //change angle according to hit location
        if (hitZone == ZONE_ONE) {
            velocity.setDx(Velocity.fromAngleAndSpeed(
                    300, DEFAULT_SPEED).getDx());
            velocity.setDy(Velocity.fromAngleAndSpeed(
                    300, DEFAULT_SPEED).getDy());
        } else if (hitZone == ZONE_TWO) {
            velocity.setDx(Velocity.fromAngleAndSpeed(
                    330, DEFAULT_SPEED).getDx());
            velocity.setDy(Velocity.fromAngleAndSpeed(
                    330, DEFAULT_SPEED).getDy());
        } else if (hitZone == ZONE_THREE) {
            velocity.setDy(-(velocity.getDy()));
        } else if (hitZone == ZONE_FOUR) {
            velocity.setDx(Velocity.fromAngleAndSpeed(
                    30, DEFAULT_SPEED).getDx());
            velocity.setDy(Velocity.fromAngleAndSpeed(
                    30, DEFAULT_SPEED).getDy());
        } else {
            velocity.setDx(Velocity.fromAngleAndSpeed(
                    60, DEFAULT_SPEED).getDx());
            velocity.setDy(Velocity.fromAngleAndSpeed(
                    60, DEFAULT_SPEED).getDy());
        }
        return velocity;
    }

    /**
     * Add paddle to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
