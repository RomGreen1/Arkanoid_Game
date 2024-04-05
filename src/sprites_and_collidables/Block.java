package sprites_and_collidables;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry_primitive.Point;
import geometry_primitive.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final int LINE_ONE = 1;
    private static final int LINE_TWO = 2;
    private static final int LINE_THREE = 3;
    private static final int LINE_FOUR = 4;
    private static final int LINE_FIVE = 5;
    private static final int LINE_SIX = 6;
    private static final int LINE_SEVEN = 7;

    private final List<HitListener> hitListeners;
    private final Point upperLeft;
    private final double width;
    private final double height;
    private final Color color;

    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle
     * @param color     the color
     */
    public Block(Rectangle rectangle, Color color) {
        this.hitListeners = new ArrayList<>();
        this.upperLeft = rectangle.getUpperLeft();
        this.width = rectangle.getWidth();
        this.height = rectangle.getHeight();
        this.color = color;
    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft the upper left point of the block
     * @param width     the width
     * @param height    the height
     * @param lineNum   line number of the block that determine the color
     */
    public Block(Point upperLeft, double width, double height, int lineNum) {
        this.hitListeners = new ArrayList<>();
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        //determine the color according to the line of the block
        if (lineNum == LINE_ONE) {
            this.color = Color.GRAY;
        } else if (lineNum == LINE_TWO) {
            this.color = Color.RED;
        } else if (lineNum == LINE_THREE) {
            this.color = Color.YELLOW;
        } else if (lineNum == LINE_FOUR) {
            this.color = Color.PINK;
        } else if (lineNum == LINE_FIVE) {
            this.color = Color.GREEN;
        } else if (lineNum == LINE_SIX) {
            this.color = Color.magenta;
        } else if (lineNum == LINE_SEVEN) {
            this.color = Color.white;
        } else {
            this.color = Color.GRAY;
        }
    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
    public Block(Point upperLeft, int width, int height, Color color) {
        this.hitListeners = new ArrayList<>();
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        this.color = color;
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game level
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * check if the collision is vertical type or not.
     *
     * @param block          the block that take part in the collision
     * @param collisionPoint the collision point
     * @return true if collision is vertical, false otherwise
     */
    public boolean verCollision(Block block, Point collisionPoint) {
        /* check if y value of collision point is equal to y value of
        one of the horizontal lines in the rectangle. if so, the collision
        is vertical type.
         */
        return collisionPoint.getY() == block.upperLeft.getY()
                || collisionPoint.getY()
                == block.upperLeft.getY() + block.height;
    }
    /**
     * get a rectangle that has the same location and size of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.upperLeft, this.width,
                this.height);
    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Draw the block on the draw surface.
     *
     * @param d the draw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) upperLeft.getX(),
                (int) upperLeft.getY(),
                (int) width, (int) height);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) upperLeft.getX(),
                (int) upperLeft.getY(),
                (int) width, (int) height);

    }

    /**
     * tell block that time has passed.
     */
    @Override
    public void timePassed() {
    }

    private void notifyHit(Ball hitter) {
        //in case of a hit, all the listeners should know about it:
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        //notify listeners about a hit event
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * handle hit by changing velocity of the ball according to the collision
     * point and block.
     *
     * @param collisionPoint collision point of the ball with the block
     * @param currentVelocity current velocity of the ball
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        //check if collision is vertical type
        if (verCollision(this, collisionPoint)) {
            //if yes, change dy sign
            currentVelocity.setDy(-(currentVelocity.getDy()));
        } else {
            //if collision is horizontal, change dx sign
            currentVelocity.setDx(-(currentVelocity.getDx()));
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

}
