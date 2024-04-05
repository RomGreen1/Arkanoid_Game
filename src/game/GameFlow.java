package game;

import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    public static final int ZERO = 0;
    private final KeyboardSensor ks;
    private final AnimationRunner ar;
    /**
     * Instantiates a new Game flow.
     * @param ar the animation runner
     * @param ks the keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
    }

    /**
     * Run levels.
     *
     * @param levels the levels we need to run
     */
    public void runLevels(List<LevelInformation> levels) {
        //to keep track of the score:
        int countScore = 0;
        // for loop on all the levels in the list
        for (LevelInformation levelInfo: levels) {
           GameLevel level = new GameLevel(this.ar, this.ks, levelInfo,
                   countScore,
                   levels.size());
           level.initialize();
           //play the level while there are enough balls and blocks
           while (level.getBallCounter() > ZERO
                   && level.getBlockCounter() > ZERO) {
               level.run();
               //save the score from the end of the level
               countScore = level.getScore().getValue();
           }
           //if the player is out of balls, the game ends
           if (level.getBallCounter() == ZERO) {
               break;
           }
        }
    }
}
