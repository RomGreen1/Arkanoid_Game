// Rom Green
// 318862711
import biuoop.GUI;
import biuoop.Sleeper;
import game.AnimationRunner;
import game.GameFlow;
import game.LevelInformation;
import levels.DirectHit;
import levels.Green3;
import levels.WideEasy;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 3 game.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Game", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60,
                new Sleeper());
        GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor());
        LevelInformation directHit = new DirectHit();
        LevelInformation wideEasy = new WideEasy();
        LevelInformation regularLevel = new Green3();
        List<LevelInformation> levelList = new ArrayList<>();
        if (args.length == 0 || args[0].equals("${args}")) {
            //if args is empty, run all the levels in their order
            levelList.add(directHit);
            levelList.add(wideEasy);
            levelList.add(regularLevel);
            gameFlow.runLevels(levelList);
        } else {
            // else, play the levels according to the input
            for (String arg : args) {
                switch (arg) {
                    case "1" -> levelList.add(directHit);
                    case "2" -> levelList.add(wideEasy);
                    case "3" -> levelList.add(regularLevel);
                    default -> {
                    }
                }
            }
            gameFlow.runLevels(levelList);
        }
    }
}
