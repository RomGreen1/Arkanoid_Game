package sprites_and_collidables;

/**
 * The type Counter.
 */
public class Counter {
    private int count = 0;
    /**
     * Increase this counter.
     *
     * @param number the number we want to add to the count
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * Decrease this counter.
     *
     * @param number the number we want to subtract from the current count
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * Gets value of count.
     *
     * @return the value of the counter
     */
    public int getValue() {
        return this.count;
    }
}
