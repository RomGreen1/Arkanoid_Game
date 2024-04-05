package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry_primitive.Point;
import sprites_and_collidables.Ball;
import sprites_and_collidables.BallRemover;
import sprites_and_collidables.Block;
import sprites_and_collidables.BlockRemover;
import sprites_and_collidables.Collidable;
import sprites_and_collidables.Counter;
import sprites_and_collidables.GameEnvironment;
import sprites_and_collidables.HitListener;
import sprites_and_collidables.Paddle;
import sprites_and_collidables.ScoreIndicator;
import sprites_and_collidables.ScoreTrackingListener;
import sprites_and_collidables.Sprite;
import sprites_and_collidables.SpriteCollection;
import sprites_and_collidables.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game.
 */
public class GameLevel implements Animation {
    /**
     * The constant PADDLE_DEFAULT_HEIGHT.
     */
    public static final int PADDLE_DEFAULT_HEIGHT = 20;
    /**
     * The constant DEFAULT_RADIUS.
     */
    public static final int DEFAULT_RADIUS = 4;
    public static final int DEFAULT_LINE_NUM = 0;
    public static final int MAX_HEIGHT = 600;
    public static final int WIDTH = 25;
    public static final int DEFAULT_WIDTH = WIDTH;
    public static final int UPPER_BLOCK_WIDTH = 800;
    public static final int UPPER_BLOCK_HEIGHT = 40;
    public static final int START_Y = 0;
    public static final int START_X = 0;
    public static final int RIGHT_BLOCK_X = 775;
    public static final int ZERO = 0;
    public static final int FINISHED_LEVEL_INCREASE = 100;
    public static final int THREE = 3;
    public static final int MIDDLE_X = 400;
    private final LevelInformation levelInfo;
    private final HitListener blockRemover;
    private final BallRemover ballRemover;
    private final ScoreTrackingListener scoreTrackingListener;
    private final ScoreIndicator scoreIndicator;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter blockCounter;
    private final Counter ballCounter;
    private final Counter score;
    private final GUI gui;
    private boolean running;
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private static int levelNumber = 0;
    private final int numberOfLevels;
    private final PauseScreen pauseScreen;
    private final EndScreen gameOver;
    /**
     * Gets ball counter.
     *
     * @return the ball counter
     */
    public int getBallCounter() {
        return ballCounter.getValue();
    }

    /**
     * Gets block counter.
     *
     * @return the block counter
     */
    public int getBlockCounter() {
        return blockCounter.getValue();
    }

    /**
     * Remove collidable void.
     *
     * @param c the collidable we want to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite we want to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Instantiates a new Game.
     *
     * @param levelInformation the level information
     * @param countScore the score from the previous games need to be kept
     * , so countScore has the updated score from the previous games
     * @param numberOfLevels the number of levels in the levels list
     * @param ks keyboard sensor
     * @param ar animation runner
     */
    public GameLevel(AnimationRunner ar, KeyboardSensor ks,
                     LevelInformation levelInformation,
                     int countScore,
                     int numberOfLevels) {
        this.gui = ar.getGui();
        this.keyboard = ks;
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.numberOfLevels = numberOfLevels;
        Counter continuedScore = new Counter();
        //continue the count of score
        continuedScore.increase(countScore);
        this.score = continuedScore;
        this.blockRemover = new BlockRemover(
                this, this.blockCounter);
        this.ballRemover = new BallRemover(this, this.ballCounter);
        this.scoreTrackingListener =
                new ScoreTrackingListener(this.score);
        this.scoreIndicator = new ScoreIndicator(this.score,
                levelInformation.levelName());
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.levelInfo = levelInformation;
        this.pauseScreen = new PauseScreen(this.keyboard);
        this.gameOver = new EndScreen(this.keyboard, GameLevel.ZERO, false);
        //animation runner
        this.runner = ar;
        this.runner.setLevelInformation(levelInformation);
    }

    /**
     * Add colllidable to game.
     *
     * @param c the collidable object
     */
    public void addCollidable(Collidable c) {
        //add the rectangle to the list of collidable objects
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite to the game.
     *
     * @param s the sprite we want to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize.
     */
    public void initialize() {
        //add blocks
        List<Block> blockList = new ArrayList<>(this.levelInfo.blocks());
        for (Block block : blockList) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        this.blockCounter.increase(this.levelInfo.numberOfBlocksToRemove());
        Block[] borderBlocks = new Block[3];
        //upper block
        borderBlocks[0] = new Block(
                new Point(0, START_Y), UPPER_BLOCK_WIDTH,
                UPPER_BLOCK_HEIGHT, DEFAULT_LINE_NUM);
        //left block
        borderBlocks[1] = new Block(new Point(
                START_X, START_Y), DEFAULT_WIDTH, MAX_HEIGHT, DEFAULT_LINE_NUM);
        //right block
        borderBlocks[2] = new Block(new Point(
                RIGHT_BLOCK_X, START_Y),
                DEFAULT_WIDTH, MAX_HEIGHT, DEFAULT_LINE_NUM);
        //add border blocks
        for (Block block : borderBlocks) {
            block.addToGame(this);
        }
        //add death-region block
        Block deathBlock = new Block(new Point(
                0, 601), 800, 10, 1);
        deathBlock.addHitListener(ballRemover);
        deathBlock.addToGame(this);
        //add balls
       int i = 0;
        for (Velocity velocity : this.levelInfo.initialBallVelocities()) {
            Ball ball = new Ball(
                    new Point(this.levelInfo.ballPoints().get(i).getX(),
                    this.levelInfo.ballPoints().get(i).getY()),
                    DEFAULT_RADIUS, velocity, this.environment);
            ball.addToGame(this);
            i++;
        }
        scoreIndicator.addToGame(this);
        this.ballCounter.increase(this.levelInfo.numberOfBalls());
        //add paddle
        Paddle paddle = new Paddle(
                new Point(
                        MIDDLE_X - (this.levelInfo.paddleWidth() / 2.0),
                        555), this.levelInfo.paddleWidth(),
                PADDLE_DEFAULT_HEIGHT, this.keyboard,
                this.levelInfo.paddleSpeed());
        paddle.addToGame(this);
    }
    /**
     * Run the game level.
     */
    public void run() {
        //run countdown
        this.runner.countDownAnimation(new CountDownAnimation(THREE,
                THREE, this.sprites));
        this.running = true;
        // run animation
        this.runner.run(this);
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //check if there are remaining block
        if (this.blockCounter.getValue() == ZERO) {
            //the player finished a level, so we add 1 to the level number
            levelNumber++;
            //add 100 for finished level
            this.score.increase(FINISHED_LEVEL_INCREASE);
            /*
            if there are no remaining blocks in the final level, it means
            the player has won, so we print the message and close the game
            */
            //if args is null and the player finished level 4
            if (this.numberOfLevels == 0 && levelNumber == 4) {
                //set win and final score
                this.gameOver.setEndScore(this.score.getValue());
                this.gameOver.setWin(true);
                //run "win" animation
                this.runner.run(new KeyPressStoppableAnimation(
                        this.keyboard, KeyboardSensor.SPACE_KEY,
                        this.gameOver));
                gui.close();
            } else if (levelNumber == this.numberOfLevels) {
                //set win and final score
                this.gameOver.setEndScore(this.score.getValue());
                this.gameOver.setWin(true);
                //run "win" animation
                this.runner.run(new KeyPressStoppableAnimation(
                        this.keyboard, KeyboardSensor.SPACE_KEY,
                        this.gameOver));
                gui.close();
            }
            this.running = false;
        }
        //check if there are remaining balls
        if (this.ballCounter.getValue() == ZERO) {
            //set final score
            this.gameOver.setEndScore(this.score.getValue());
            // run "game over" animation
            this.runner.run(new KeyPressStoppableAnimation(
                    this.keyboard, KeyboardSensor.SPACE_KEY, this.gameOver));
            gui.close();
            this.running = false;
        }
        this.levelInfo.drawBackground(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            //if "p" is pressed, run "pause" animation
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, this.pauseScreen));
        }
    }
    @Override
    public boolean shouldStop() {
        return this.running;
    }
}
