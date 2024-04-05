package sprites_and_collidables;

import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on surface.
     *
     * @param d the draw surface
     */
    void drawOn(DrawSurface d);

    /**
     * note all the sprites that time has passed.
     */
    void timePassed();
}
