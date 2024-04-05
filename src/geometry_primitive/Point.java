package geometry_primitive;
/**
 * The type Point.
 */
public class Point {
    private final double x;
    private final double y;
    public static final double EPSILON = 0.00001;
    /**
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
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculates the distance between this point to the other.
     *
     * @param other the other point
     * @return the distance between the points
     */
    public double distance(Point other) {
        double p1x = this.x;
        double p1y = this.y;
        //return the distance between the two points
        return Math.sqrt((p1x - other.x) * (p1x - other.x)
                + (p1y - other.y) * (p1y - other.y));
    }

    /**
     * the method checks if this point and other point are equal.
     *
     * @param other the other point
     * @return true if two point are equal, and false if not
     */
    public boolean equals(Point other) {
        return (doubleEquals(this.x, other.x)
                && doubleEquals(this.y, other.y));
    }
    /**
     * Gets x of point.
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y of point.
     *
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }
}
