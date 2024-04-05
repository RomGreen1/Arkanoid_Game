package sprites_and_collidables;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;


/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    public static final int DEFAULT_FONT = 15;
    public static final int DEFAULT_HEIGHT = 20;
    public static final int START_Y = 0;
    public static final int START_X = 0;
    private final Counter scoreCounter;
    private final String levelName;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scoreCounter the score counter
     * @param levelName  we need the level name for the print
     */
    public ScoreIndicator(Counter scoreCounter, String levelName) {
        this.scoreCounter = scoreCounter;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(START_X, START_Y, d.getWidth(), DEFAULT_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawRectangle(START_X, START_Y, d.getWidth(), DEFAULT_HEIGHT);
        //print text
        d.drawText(380, 15,
                "Score: " + this.scoreCounter.getValue(), DEFAULT_FONT);
        d.drawText(600, 15, this.levelName, DEFAULT_FONT);
    }

    @Override
    public void timePassed() {
    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
