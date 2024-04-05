package game;

import biuoop.DrawSurface;
import sprites_and_collidables.SpriteCollection;
import java.awt.Color;

/**
 * The type Count down animation.
 */
public class CountDownAnimation implements Animation {
    /**
     * The constant MIDDLE_X.
     */
    public static final int MIDDLE_X = 400;
    /**
     * The constant MIDDLE_Y.
     */
    public static final int MIDDLE_Y = 300;
    public static final int BIG_FONT = 50;
    private final double numOfSeconds;
    private final int countFrom;
    private int downCounter;
    private final SpriteCollection gameScreen;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountDownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.downCounter = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        //draw screen
        this.gameScreen.drawAllOn(d);
        //draw how many seconds are left before the game starts
        d.setColor(Color.GRAY);
        d.drawText(MIDDLE_X, MIDDLE_Y, "" + this.downCounter, BIG_FONT);
        // decrease the counter by 1 to continue the countdown
        this.downCounter--;
    }

    @Override
    public boolean shouldStop() {
        //if count reached to 0, we should stop
        return this.downCounter != 0;
    }

    /**
     * Gets num of seconds.
     *
     * @return the num of seconds we want to make the count
     */
    public double getNumOfSeconds() {
        return numOfSeconds;
    }

    /**
     * Gets count from.
     *
     * @return the count from which number
     */
    public double getCountFrom() {
        return countFrom;
    }
}
