package tests;

import sprites_and_collidables.Ball;
import sprites_and_collidables.Block;
import sprites_and_collidables.HitListener;

/**
 * The type Printing hit listener.
 */
public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
