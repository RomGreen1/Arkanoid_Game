package levels;

import biuoop.DrawSurface;
import game.LevelInformation;
import geometry_primitive.Point;
import geometry_primitive.Rectangle;
import sprites_and_collidables.Block;
import sprites_and_collidables.Sprite;
import sprites_and_collidables.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Regular level.
 */
public class Green3 implements LevelInformation {

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v1 = new Velocity(0, 5);
        Velocity v2 = new Velocity(2, 5);
        Velocity v3 =  new Velocity(-2, 5);
        List<Velocity> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        list.add(v3);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Level Name: Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Block(
                new Rectangle(new Point(0, 0), 800, 600),
                this.getColor());
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Point[] points = new Point[50];
        //first line
        points[0] = new Point(175, 100);
        points[1] = new Point(225, 100);
        points[2] = new Point(275, 100);
        points[3] = new Point(325, 100);
        points[4] = new Point(375, 100);
        points[5] = new Point(425, 100);
        points[6] = new Point(475, 100);
        points[7] = new Point(525, 100);
        points[8] = new Point(575, 100);
        points[9] = new Point(625, 100);
        points[10] = new Point(675, 100);
        points[11] = new Point(725, 100);
        //second line
        points[12] = new Point(225, 125);
        points[13] = new Point(275, 125);
        points[14] = new Point(325, 125);
        points[15] = new Point(375, 125);
        points[16] = new Point(425, 125);
        points[17] = new Point(475, 125);
        points[18] = new Point(525, 125);
        points[19] = new Point(575, 125);
        points[20] = new Point(625, 125);
        points[21] = new Point(675, 125);
        points[22] = new Point(725, 125);
        //third line
        points[23] = new Point(275, 150);
        points[24] = new Point(325, 150);
        points[25] = new Point(375, 150);
        points[26] = new Point(425, 150);
        points[27] = new Point(475, 150);
        points[28] = new Point(525, 150);
        points[29] = new Point(575, 150);
        points[30] = new Point(625, 150);
        points[31] = new Point(675, 150);
        points[32] = new Point(725, 150);
        //fourth line
        points[33] = new Point(325, 175);
        points[34] = new Point(375, 175);
        points[35] = new Point(425, 175);
        points[36] = new Point(475, 175);
        points[37] = new Point(525, 175);
        points[38] = new Point(575, 175);
        points[39] = new Point(625, 175);
        points[40] = new Point(675, 175);
        points[41] = new Point(725, 175);
        //fifth line
        points[42] = new Point(375, 200);
        points[43] = new Point(425, 200);
        points[44] = new Point(475, 200);
        points[45] = new Point(525, 200);
        points[46] = new Point(575, 200);
        points[47] = new Point(625, 200);
        points[48] = new Point(675, 200);
        points[49] = new Point(725, 200);
        //add blocks
        for (Point point : points) {
            //determine color according to line of ball
            int lineNum = 0;
            if (point.getY() == 100) {
                lineNum = 1;
            } else if (point.getY() == 125) {
                lineNum = 2;
            } else if (point.getY() == 150) {
                lineNum = 3;
            } else if (point.getY() == 175) {
                lineNum = 4;
            } else if (point.getY() == 200) {
                lineNum = 5;
            }
            Block block = new Block(point, 50, 25, lineNum);
            list.add(block);
        }
        return list;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public void drawBackground(DrawSurface d) {
        d.setColor(Color.getColor(
                "dark green", new Color(0, 130, 0)));
        d.fillRectangle(0, 0, 800, 600);
    }

    @Override
    public List<Point> ballPoints() {
        List<Point> list = new ArrayList<>();
        Point ballPoint1 = new Point(395, 500);
        Point ballPoint2 = new Point(400, 500);
        Point ballPoint3 = new Point(405, 500);
        list.add(ballPoint1);
        list.add(ballPoint2);
        list.add(ballPoint3);
        return list;
    }
}
