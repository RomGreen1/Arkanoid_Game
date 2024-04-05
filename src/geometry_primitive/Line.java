package geometry_primitive;

/**
 * The type Line.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * check if the difference between a and b is less than 0.00001.
     *
     * @param a the a
     * @param b the b
     * @return true if the difference between the numbers is less than 0.00001 ,false otherwise.
     */
    public static boolean doubleEquals(double a, double b) {
        return  Math.abs(a - b) < Point.EPSILON;
    }

    /**
     * Instantiates a new Line, using the start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Instantiates a new Line, using x and y values.
     *
     * @param x1 the x value of the start of the line
     * @param y1 the y value of the start of the line
     * @param x2 the x value of the end of the line
     * @param y2 the y value of the end of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * the method returns the starting point of the line.
     *
     * @return the start point
     */
    public Point start() {

        return new Point(this.start.getX(), this.start.getY());
    }
    /**
     * checks if lines intersect.
     *
     * @param other the other line
     * @return true if the lines intersect, otherwise false
     */
    public boolean isIntersecting(Line other) {
        double x;
        double y;
        //check for vertical lines
        if (isVertical(this) && isVertical(other)) {
            //if both lines are vertical
            double lowerOtherY = Math.max(other.start.getY(), other.end.getY());
            double upperOtherY = Math.min(other.start.getY(), other.end.getY());
            //check if lines intersect
            if (this.start.getX() == other.start.getX()) {
                /*if lines have the same x value
                if y values of lines are equal in some point
                , return true, false otherwise
                 */
                return (this.start.getY() >= upperOtherY
                        && this.start.getY() <= lowerOtherY)
                        || (this.end.getY() >= upperOtherY
                        && this.end.getY() <= lowerOtherY);
            }
            //if this line is vertical
            } else if (isVertical(this)) {
                double m2 = (other.start.getY() - other.end.getY())
                        / (other.start.getX() - other.end.getX());
                double b2 = other.start.getY() - m2 * other.start.getX();
                double intersectionX = this.start.getX();
                double intersectionY = m2 * intersectionX + b2;
                //check if intersection is in range of the lines
                return inRange(intersectionX, intersectionY, this.start,
                        this.end, other.start, other.end);
                //if other line is vertical
            } else if (isVertical(other)) {
                double m1 = ((this.start.getY() - this.end.getY())
                        / (this.start.getX() - this.end.getX()));
                double b1 = this.start.getY() - m1 * this.start.getX();
                double intersectionX = other.start.getX();
                double intersectionY = m1 * intersectionX + b1;
                //check if intersection is in range of the lines
                return inRange(intersectionX, intersectionY, this.start,
                        this.end, other.start, other.end);
            //if both lines are not vertical
        } else {
                //calculate slopes
                double m1 = ((this.start.getY() - this.end.getY())
                        / (this.start.getX() - this.end.getX()));
                double m2 = (other.start.getY() - other.end.getY())
                        / (other.start.getX() - other.end.getX());
                //calculate b of line
                double b1 = this.start.getY() - m1 * this.start.getX();
                double b2 = other.start.getY() - m2 * other.start.getX();
                if (m1 == m2 && this.equals(other)) {
                    //if lines are identical
                    return true;
                }
                if (m1 == m2 && this.start.getY() == this.end.getY()
                && other.start.getY() == other.end.getY()) {
                    //if lines are horizontal
                    return checkX(this, other);
                }
                if (m1 == m2 && checkOverlap(this, other)) {
                    //if lines have infinite intersection points
                    return true;
                }
                if (m1 == m2) {
                    return false;
                } else {
                    //calculate intersection point
                    x = (b2 - b1) / (m1 - m2);
                    y = m1 * x + b1;
                    //check if intersection point is in range
                    return inRange(x, y, this.start, this.end,
                            other.start, other.end);
                }
            }
            return false;
        }

    /**
     * In range boolean.
     *
     * @param x          the x value of intersection point
     * @param y          the y value of intersection point
     * @param thisStart  this line start point
     * @param thisEnd    this line end point
     * @param otherStart other line start point
     * @param otherEnd   other line end point
     * @return true if x and y are in range of the lines
     */
    public boolean inRange(double x, double y, Point thisStart, Point thisEnd,
                           Point otherStart, Point otherEnd) {
        //check if x value is in range of the lines
        if (x < Math.min(thisStart.getX(), thisEnd.getX())
                || x > Math.max(thisStart.getX(), thisEnd.getX())
                || x < Math.min(otherStart.getX(), otherEnd.getX())
                || x > Math.max(otherStart.getX(), otherEnd.getX())) {
            return false;
        }
        //check if y value is in range of the lines
        return !(y < Math.min(thisStart.getY(), thisEnd.getY()))
                && !(y > Math.max(thisStart.getY(), thisEnd.getY()))
                && !(y < Math.min(otherStart.getY(), otherEnd.getY()))
                && !(y > Math.max(otherStart.getY(), otherEnd.getY()));
    }
    /**
     * check if a line is vertical or not, using the x value
     * of the start and end point of the line.
     *
     * @param line the line
     * @return true if line is vertical, false otherwise
     */
    public boolean isVertical(Line line) {
        return  (doubleEquals(line.start.getX(), line.end.getX()));
    }

    /**
     * Check overlap boolean.
     *
     * @param l1 line 1
     * @param l2 line 2
     * @return true if lines have one or more common point
     */
    public boolean checkOverlap(Line l1, Line l2) {
        //calculate slopes
        double m1 = ((l1.start.getY() - l1.end.getY())
                / (l1.start.getX() - l1.end.getX()));
        double m2 = (l2.start.getY() - l2.end.getY())
                / (l2.start.getX() - l2.end.getX());
        //calculate b of lines
        double b1 = l1.start.getY() - m1 * l1.start.getX();
        double b2 = l2.start.getY() - m2 * l2.start.getX();
        //calculate which edge of the line is smaller and which is bigger
        double l2MinX = Math.min(l2.start.getX(), l2.end.getX());
        double l2MaxX = Math.max(l2.start.getX(), l2.end.getX());
        if (m1 == m2 && b1 == b2) {
            //if l1 is inside l2 for one point or more
            return (l1.end.getX() >= l2MinX && l1.end.getX() <= l2MaxX)
                    || (l1.start.getX() >= l2MinX && l1.start.getX()
                    <= l2MaxX);
        }
        return false;
    }

    /**
     * in case of horizontal lines, we check if the lines have
     * common point.
     *
     * @param l1 line 1
     * @param l2 line 2
     * @return true if lines have common point, false otherwise
     */
    public boolean checkX(Line l1, Line l2) {
        /*
        in this  case, both lines are horizontal, we need to
        check if they have one or more common points
        */
        //check min and max points of lines
        double l1Max = Math.max(l1.start.getX(), l1.end.getX());
        double l1Min = Math.min(l1.start.getX(), l1.end.getX());
        double l2Max = Math.max(l2.start.getX(), l2.end.getX());
        double l2Min = Math.min(l2.start.getX(), l2.end.getX());
        if ((l2Min < l1Max && l2Min > l1Min)
                || (l1Max > l2Max && l1Min < l2Max)) {
            //if lines have infinite intersection points
            return true;
        }
        //if lines touch each other only at the edges
        if (doubleEquals(l1Max, l2Min)) {
            return true;
        }
        return doubleEquals(l2Max, l1Min);
    }

    /**
     * in case of vertical lines, we check if the lines have
     * common point. if they have exactly one intersection point
     * we will return it.
     *
     * @param l1 line 1
     * @param l2 line 2
     * @return the intersection point if lines have exactly one common point,
     * null otherwise
     */
    public Point checkY(Line l1, Line l2) {
        //check min and max points of lines
        double l1Max = Math.max(l1.start.getY(), l1.end.getY());
        double l1Min = Math.min(l1.start.getY(), l1.end.getY());
        double l2Max = Math.max(l2.start.getY(), l2.end.getY());
        double l2Min = Math.min(l2.start.getY(), l2.end.getY());
        if ((l2Min < l1Max && l2Min > l1Min)
                || (l1Max > l2Max && l1Min < l2Max)) {
            //if lines have infinite intersection points
            return null;
        }
        //if lines touch each other only at the edges
        if (doubleEquals(l1Max, l2Min)) {
            return new Point(l1.start.getX(), l2Min);
        }
        if (doubleEquals(l2Max, l1Min)) {
            return new Point(l1.start.getX(), l2Max);
        }
        return null;
    }

    /**
     * in this case, we know the lines are intersecting at one point
     * , and one line is vertical. the method calculates the intersection point
     *
     * @param verLine the vertical line
     * @param line    the other line
     * @return the intersection point
     */
    public Point checkOneVerLine(Line verLine, Line line) {
        double m2 = (line.start.getY() - line.end.getY())
                / (line.start.getX() - line.end.getX());
        double b2 = line.start.getY() - m2 * line.start.getX();
        double intersectionX = verLine.start.getX();
        double intersectionY = m2 * intersectionX + b2;
        return new Point(intersectionX, intersectionY);
        }

    /**
     * in this case, we know the lines have one or more common points,
     * if we find the lines have exactly one common point , return the point,
     * if more, return null.
     *
     * @param l1 line 1
     * @param l2 line 2
     * @return the intersection point or null
     */
    public Point checkOnePoint(Line l1, Line l2) {
        /*
        here we know the lines have one or more common points,
        if we find the lines have exactly one common point , return the point,
        if more, return null
         */
        //calculate slopes
        double m1 = ((l1.start.getY() - l1.end.getY())
                / (l1.start.getX() - l1.end.getX()));
        double m2 = (l2.start.getY() - l2.end.getY())
                / (l2.start.getX() - l2.end.getX());
        //calculate b value of lines
        double b1 = l1.start.getY() - m1 * l1.start.getX();
        double b2 = l2.start.getY() - m2 * l2.start.getX();
        //check min and max x of lines
        double l2MinX = Math.min(l2.start.getX(), l2.end.getX());
        double l2MaxX = Math.max(l2.start.getX(), l2.end.getX());
        double l1MinX = Math.min(l1.start.getX(), l1.end.getX());
        double l1MaxX = Math.max(l1.start.getX(), l1.end.getX());
        if (m1 == m2 && b1 == b2) {
            if ((l1.end.getX() > l2MinX && l1.end.getX() < l2MaxX)
                    || (l1.start.getX() > l2MinX && l1.start.getX() < l2MaxX)) {
                //if l1 is inside l2 for more than one point
                return null;
            }
            if ((l2.end.getX() > l1MinX && l2.end.getX() < l1MaxX)
                    || (l2.start.getX() > l1MinX && l2.start.getX() < l1MaxX)) {
                return null;
            }
        }
        //check if lines are identical, if yes, return null
        if (l1.equals(l2)) {
            return null;
        }
        //now we know the lines have one common point, we need to find it
        if (l1.start.equals(l2.start)) {
            return l1.start;
        } else if (l1.end.equals(l2.start)) {
            return l1.end;
        } else if (l1.end.equals(l2.end)) {
            return l1.end;
        } else if (l1.start.equals(l2.end)) {
            return l1.start;
        }
        return null;
    }

    /**
     * check Intersection of this line with other line.
     *
     * @param other the other line
     * @return the intersection point if the lines intersect,
     * and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            //if both lines are vertical
            if (isVertical(this) && isVertical(other)) {
                //check if the lines touch each other
                return checkY(this, other);
                //check if this line is vertical
            } else if (isVertical(this)) {
                return checkOneVerLine(this, other);
                //check if other line is vertical
            } else if (isVertical(other)) {
                return checkOneVerLine(other, this);
            }
            //if both lines are not vertical
            double m1 = ((this.start.getY() - this.end.getY())
                    / (this.start.getX() - this.end.getX()));
            double m2 = (other.start.getY() - other.end.getY())
                    / (other.start.getX() - other.end.getX());
            double b1 = this.start.getY() - m1 * this.start.getX();
            double b2 = other.start.getY() - m2 * other.start.getX();

            //if there are infinite intersection points
            if (m1 == m2 && b1 == b2) {
                if (checkOverlap(this, other)) {
                    //if lines have one or more common points
                    return checkOnePoint(this, other);
                }
            }
            //calculate intersection point
            double x = (b2 - b1) / (m1 - m2);
            double y = m1 * x + b1;
            return new Point(x, y);
        }
        return null;
    }

    /**
     * checks if lines are equal.
     *
     * @param other the other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        /*
        the start of the first line might be the end of the second line and
        vice versa. in that case the lines are still equal.
         */
        } else {
            return this.start.equals(other.end)
                    && this.start.equals(other.end);
        }
    }

    /**
     * Closest intersection to start of line point.
     * the method calculates all the intersection points of the rectangle
     * with the line and check which point is the closest to the start
     * of line.
     * @param rect the rectangle we need to check
     * @return the closest intersection to start of line point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point upperLeft = new Point(rect.getUpperLeft().getX(),
                rect.getUpperLeft().getY());
        Rectangle rectangle = new Rectangle(upperLeft,
                rect.getWidth(), rect.getHeight());
        //check if line intersect with rectangle
        java.util.List<Point> list = rectangle.intersectionPoints(this);

        double minDistance = 0;
        Point closestPoint = null;
        //search the closest intersection point to start of line
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                if (i == 0) {
                    minDistance = list.get(i).distance(this.start);
                    closestPoint = new Point(list.get(i).getX(),
                            list.get(i).getY());
                }
                if (list.get(i).distance(this.start) < minDistance) {
                    minDistance = list.get(i).distance(this.start);
                    closestPoint = new Point(list.get(i).getX(),
                            list.get(i).getY());
                }
            }
        }
        return closestPoint;
    }
}

