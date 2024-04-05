package geometry_primitive;
import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle {
    public static final int NUMBER_OF_LINES = 4;
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }
    /**
     * The method get a rectangle and make an array of the lines that
     * construct the rectangle.
     *
     * @param rectangle the rectangle we need its lines
     * @return the array of the lines of the rectangle
     */
    public Line[] makeLines(Rectangle rectangle) {
        Line[] lines = new Line[NUMBER_OF_LINES];
        //horizontal first line
        lines[0] = new Line(rectangle.upperLeft.getX(),
                rectangle.upperLeft.getY(), rectangle.upperLeft.getX()
                + rectangle.width, rectangle.upperLeft.getY());
        //horizontal other line
        lines[1] = new Line(rectangle.upperLeft.getX(),
                rectangle.upperLeft.getY() + rectangle.height,
                rectangle.upperLeft.getX()
                + rectangle.width, rectangle.upperLeft.getY()
                + rectangle.height);
        //vertical left line
        lines[2] = new Line(rectangle.upperLeft.getX(),
                rectangle.upperLeft.getY(), rectangle.upperLeft.getX(),
                rectangle.upperLeft.getY() + rectangle.height);
        lines[3] = new Line(rectangle.upperLeft.getX() + rectangle.width,
                rectangle.upperLeft.getY(), rectangle.upperLeft.getX()
                + rectangle.width,
                rectangle.upperLeft.getY() + rectangle.height);
        return lines;
    }
    /**
     * The method calculates the intersection points of the ball trajectory
     * line with the rectangle and return a list of those points.
     *
     * @param line the line of the ball movement
     * @return the list of intersection points of the line with the rectangle
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //create 4 lines that are the same to the rectangle
        Line[] recLines = makeLines(this);
        //initialize list of intersection points
        java.util.List<Point> list = new ArrayList<>();
        //for every line in the rectangle, check intersection points with line
        for (int i = 0; i < NUMBER_OF_LINES; i++) {
            //check if lines are intersecting
            if (recLines[i].isIntersecting(line)) {
                //find intersection point
                Point intersectionPoint = recLines[i].intersectionWith(line);
                //make a list of all the intersection points
                list.add(intersectionPoint);
            }
        }
        //return the list of intersection points of the rectangle with the line
        return list;
    }
}
