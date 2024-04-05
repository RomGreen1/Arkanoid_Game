package sprites_and_collidables;

import game.GameLevel;

/**
 * The type Block remover.
 */
public class BlockRemover implements HitListener {
    public static final int ONE = 1;
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel     the game level
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * handle hit event by removing the block from the game,
     * remove the listener from the block(that being hit) listeners and
     * decrease by 1 the number of block remaining.
     *
     * @param beingHit the block that being hit
     * @param hitter   the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        //now we deleted a block, so we need to decrease one from the count
        this.remainingBlocks.decrease(ONE);
    }
}
