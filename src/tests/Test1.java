/*
package tests;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import sprites_and_collidables.GameEnvironment;
import sprites_and_collidables.Collidable;
import sprites_and_collidables.Velocity;
import sprites_and_collidables.Block;
import sprites_and_collidables.Ball;
import geometry_primitive.Point;


*/
/**
 * The type Main.
 *//*

public class Test1 {
    */
/**
     * The entry point of application.
     *
     * @param args the input arguments
     *//*

    public static void main(String[] args) {
        GUI gui = new GUI("nextGame", 500, 500);
        Sleeper sleeper = new Sleeper();
        Point endRec = new Point(500, 500);
        GameEnvironment gameEnvironment = new GameEnvironment();
        //Ball ball = new Ball(4, endRec);
        Collidable b1 = new Block(new Point(50, 50), 100, 100, 0);
        Collidable b2 = new Block(new Point(300, 200), 10, 50, 0);
        Collidable b3 = new Block(new Point(400, 400), 100, 50, 0);
        Collidable lowerBlock = new Block(new Point(0, 500), 500, 50, 0);
        Collidable upperBlock = new Block(new Point(0, 0), 500, 0, 0);
        Collidable leftBlock = new Block(new Point(0, 0), 0, 500, 0);
        Collidable rightBlock = new Block(new Point(500, 0), 50, 500, 0);
        gameEnvironment.addCollidable(b1);
        //gameEnvironment.addColllidable(b2);
        gameEnvironment.addCollidable(b3);
        gameEnvironment.addCollidable(lowerBlock);
        gameEnvironment.addCollidable(upperBlock);
        gameEnvironment.addCollidable(leftBlock);
        gameEnvironment.addCollidable(rightBlock);
        Ball ball = new Ball(new Point(20, 20), 4,
                new Velocity(4, 7), gameEnvironment);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            for (Collidable collidable : gameEnvironment.getCollidables()) {
                Block block = (Block) collidable;
                block.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}*/
