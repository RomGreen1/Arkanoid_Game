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
 * The type Direct hit level.
 */
public class DirectHit implements LevelInformation {
    @Override
    public Color getColor() {
        //get color for background
        return Color.BLACK;
    }

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v = new Velocity(0, 5);
        List<Velocity> list = new ArrayList<>();
        list.add(v);
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
        return "Level Name: Direct Hit";
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
        Block block = new Block(
                new Point(400, 200), 20, 20, Color.RED);
        list.add(block);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public void drawBackground(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(410, 210, 50);
        d.drawCircle(410, 210, 75);
        d.drawCircle(410, 210, 100);
    }
    @Override
    public List<Point> ballPoints() {
        List<Point> list = new ArrayList<>();
        Point point = new Point(400, 500);
        list.add(point);
        return list;
    }
}
