package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    public static final int DIGIT_TIME = 666;
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;
    private LevelInformation levelInformation;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui             the gui
     * @param framesPerSecond the frames per second
     * @param sleeper         the sleeper
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.sleeper = sleeper;
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Sets level information.
     *
     * @param levelInformation the level information
     */
    public void setLevelInformation(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }

    /**
     * get gui.
     *
     * @return this gui
     */
    public GUI getGui() {
        return gui;
    }
    /**
     * Run the animation.
     *
     * @param animation the animation we want to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Count down animation.
     *
     * @param animation the animation of count down
     */
    public void countDownAnimation(CountDownAnimation animation) {
        while (animation.shouldStop()) {
            DrawSurface d = this.gui.getDrawSurface();
            this.levelInformation.drawBackground(d);
            animation.doOneFrame(d);
            gui.show(d);
            // wait for (numOfSeconds / countFrom) between each number
            this.sleeper.sleepFor((long) (DIGIT_TIME
                    * (animation.getNumOfSeconds()
                    / animation.getCountFrom())));
        }
    }
}
