package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    public static final int TWO = 2;
    public static final int DEFAULT_FONT = 32;
    public static final int DEFAULT_X_TO_START = 165;
    private final boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the k
     */
    public PauseScreen(KeyboardSensor k) {
        this.stop = false;
    }
    /**
     * Do one frame. in one frame, all the balls are moving and blocks
     * disappear if they get hit.
     *
     * @param d the draw surface we move on
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(DEFAULT_X_TO_START, d.getHeight() / TWO,
                "paused -- press space to continue", DEFAULT_FONT);
    }
    /**
     * the method checks if the level should stop or not.
     *
     * @return true to stop, false to continue
     */
    public boolean shouldStop() {
        return !this.stop; }
}