package sprites_and_collidables;

import geometry_primitive.Point;

/**
 * The type Velocity.
 */
public class Velocity {
    /**
     * The Zero.
     */
    static final double ZERO = 0;
    /**
     * The x value we want to move forward.
     */
    private double dx;

    /**
     * Sets dx.
     *
     * @param dx the dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * The x value we want to move upwards.
     */
    private double dy;
    /**
     * Sets dy.
     *
     * @param dy the dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Gets dy.
     *
     * @return the dy value of the velocity
     */
    public double getDy() {
        return dy;
    }

    /**
     * Gets dx.
     *
     * @return the dx value of the velocity
     */
    public double getDx() {
        return dx;
    }

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx value of the velocity
     * @param dy the dy value of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Double equals boolean.
     *
     * @param a the first number
     * @param b the second number
     * @return true if the difference between the numbers is less than 0.00001
     * , false otherwise.
     */
    public static boolean doubleEquals(double a, double b) {
        return  Math.abs(a - b) < Point.EPSILON;
    }

    /**
     * converts from angle and speed velocity to dx, dy.
     *
     * @param angle the wanted angle
     * @param speed the wanted speed
     * @return the velocity with dx, dy values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //convert angel to radians
        double realAngel = Math.toRadians(angle);
        //calculate dx
        double dx = speed * Math.sin(realAngel);
        if (doubleEquals(dx, ZERO)) {
            dx = ZERO;
        }
        //calculate dy
        double dy = speed * -(Math.cos(realAngel));
        if (doubleEquals(dy, ZERO)) {
            dy = ZERO;
        }
        return new Velocity(dx, dy);
    }
    /**
     * Apply to point using velocity of the ball.
     *
     * @param p the current point
     * @return the updated point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

}
