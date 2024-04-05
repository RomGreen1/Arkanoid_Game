package game;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame. in one frame, all the balls are moving and blocks
     * disappear if they get hit.
     *
     * @param d the draw surface we move on
     */
    void doOneFrame(DrawSurface d);

    /**
     * the method checks if the level should stop or not.
     *
     * @return true to stop, false to continue
     */
    boolean shouldStop();
}
