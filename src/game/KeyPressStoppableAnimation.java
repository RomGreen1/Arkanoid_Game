package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        //if key is not pressed now, set to false
        if (!this.sensor.isPressed(this.key)) {
            setAlreadyPressed(false);
        }
        //if key is already pressed before the animation started, do not stop
        if (this.isAlreadyPressed) {
            return;
        }
        /*
        if we got here, it means that if the key was pressed, the player want
        it to be pressed, and it is not a mistake, so if key is pressed - stop
        the animation
         */
        if (this.sensor.isPressed(this.key)) {
            this.stop = true;
        }
    }

    /**
     * Sets already pressed.
     *
     * @param isAlreadyPressed the is already pressed
     */
    public void setAlreadyPressed(boolean isAlreadyPressed) {
        this.isAlreadyPressed = isAlreadyPressed;
    }

    @Override
    public boolean shouldStop() {
        return !this.stop;
    }
}
