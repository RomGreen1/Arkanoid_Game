package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private int endScore;
    private boolean win;
    private final boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k        the k
     * @param endScore the final score
     * @param win      indicates if the player won or not
     */
    public EndScreen(KeyboardSensor k, int endScore, boolean win) {
        this.win = win;
        this.endScore = endScore;
        this.stop = false;
    }

    /**
     * Sets end score.
     *
     * @param endScore the end score
     */
    public void setEndScore(int endScore) {
        this.endScore = endScore;
    }
    /**
     * Sets win.
     *
     * @param win the win
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.win) {
            //in case of win
            d.drawText(165, d.getHeight() / 2, "You Win! Your score"
                    + " is " + this.endScore, 32);
        } else {
            //in case of loss
            d.drawText(165, d.getHeight() / 2, "Game Over"
                    + ". Your score is " + this.endScore, 32);
        }
    }

    @Override
    public boolean shouldStop() {
        //return if the level should stop or not
        return !this.stop;
    }
}
