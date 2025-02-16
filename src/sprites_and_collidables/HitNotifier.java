package sprites_and_collidables;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     *
     * @param hl the hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
    void removeHitListener(HitListener hl);
}
