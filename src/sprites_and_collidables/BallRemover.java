package sprites_and_collidables;

import game.GameLevel;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    public static final int ONE = 1;
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel        the game
     * @param removedBalls the removed balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }
    /**
     * handle hit event by removing the ball from the game and
     * decrease by 1 the number of balls.
     *
     * @param beingHit the block that being hit
     * @param hitter   the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        /*
        we removed a ball from the game, so we have to decrease one from the
        ball count
         */
        this.remainingBalls.decrease(ONE);
    }
}
