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
import java.util.Arrays;
import java.util.List;

/**
 * The type RainBow easy level.
 */
public class WideEasy implements LevelInformation {

    public static final int HEIGHT_OF_BLOCKS = 275;
    public static final int BLOCK_WIDTH = 50;
    public static final int LEFT_BLOCK_POINT = 25;
    public static final int BLOCK_HEIGHT = 25;

    @Override
    public Color getColor() {
        //get background color
        return Color.WHITE;
    }

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity[] velocities = new Velocity[10];
        velocities[0] = new Velocity(-4, -4);
        velocities[1] = new Velocity(-3.5, -4);
        velocities[2] = new Velocity(-3, -4);
        velocities[3] = new Velocity(-2, -4);
        velocities[4] = new Velocity(-1, -4);
        velocities[5] = new Velocity(1, -4);
        velocities[6] = new Velocity(2, -4);
        velocities[7] = new Velocity(3, -4);
        velocities[8] = new Velocity(3.5, -4);
        velocities[9] = new Velocity(4, -4);
        return new ArrayList<>(Arrays.asList(velocities));
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Level name: Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Block(
                new Rectangle(new Point(0, 0), 800, 600),
                Color.white);
    }

    @Override
    public List<Block> blocks() {
        Block[] blocks = new Block[15];
        Color color = Color.RED;
        for (int i = 0; i < blocks.length; i++) {
            //change color according to index
            if (i > 1 && i <= 3) {
                color = Color.ORANGE;
            } else if (i > 3 && i <= 5) {
                color = Color.YELLOW;
            } else if (i > 5 && i <= 8) {
                color = Color.GREEN;
            } else if (i > 8 && i <= 10) {
                color = Color.BLUE;
            } else if (i > 10 && i <= 12) {
                color = Color.PINK;
            } else if (i > 12) {
                color = Color.CYAN;
            }
            //create block
            blocks[i] =
                    new Block(new Point(LEFT_BLOCK_POINT + i * BLOCK_WIDTH,
                            HEIGHT_OF_BLOCKS),
                            BLOCK_WIDTH, BLOCK_HEIGHT, color);
        }
        return new ArrayList<>(Arrays.asList(blocks));
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public void drawBackground(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, 800, 600);
        //draw sun
        d.setColor(Color.yellow);
        d.fillCircle(100, 120, 75);
        //draw clouds
        d.setColor(Color.LIGHT_GRAY);
        d.drawCircle(400, 100, 25);
        d.fillCircle(400, 100, 25);
        d.drawCircle(430, 120, 20);
        d.fillCircle(430, 120, 20);
        d.drawCircle(460, 90, 25);
        d.fillCircle(460, 90, 25);
        d.drawCircle(430, 90, 25);
        d.fillCircle(430, 90, 25);
        d.drawCircle(465, 110, 25);
        d.fillCircle(465, 110, 25);
        d.drawCircle(700, 100, 30);
        d.fillCircle(700, 100, 30);
        d.drawCircle(730, 120, 25);
        d.fillCircle(730, 120, 25);
        d.drawCircle(760, 90, 30);
        d.fillCircle(760, 90, 30);
        d.drawCircle(730, 90, 30);
        d.fillCircle(730, 90, 30);
        d.drawCircle(765, 110, 30);
        d.fillCircle(765, 110, 30);
    }

    @Override
    public List<Point> ballPoints() {
        ArrayList<Point> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Point point = new Point(400, 550);
            arrayList.add(point);
        }
        return arrayList;
    }
}
