package sprites_and_collidables;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    public static final int HIT_SCORE = 5;
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * handle hit event by increasing the score by 5.
     *
     * @param beingHit the block that being hit
     * @param hitter   the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(HIT_SCORE);
    }
}
