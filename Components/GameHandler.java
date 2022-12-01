package Components;

import java.util.Hashtable;

import Functions.FileManipulator;
import Functions.StringManipulator;
import Functions.TimeManipulator;
import Functions.timeManipulator;

public class GameHandler {
    private static Game game = null;
    private static FileManipulator fileManipulator = null;
    private static StringManipulator stringManipulator;
    private static TimeManipulator timeManipulator = null;
    private static Hashtable<String, String> introScreens = new Hashtable<String, String>();

    public GameHandler() {
        makeFileManipulator();
        makeStringManipulator();
        makeTimeManipulator();
        makeIntroScreens();
        makeGame();
    }

    public processGameHandler() {
        // One for whole game
        // One for single level
    }

    private playIntro() {

    }

    private Game makeGame() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }
    private FileManipulator makeFileManipulator() {
        if (fileManipulator == null) {
          fileManipulator = new FileManipulator();
        }
        return fileManipulator;
    }
    private void makeStringManipulator() {
        stringManipulator = new StringManipulator();
    }
    private void makeTimeManipulator() {
        timeManipulator = new TimeManipulator();
    }
    private void makeIntroScreens() {
        String tempIntroScreenOne = fileManipulator.readFile("./Screens/Intro/intro1.txt", 0, 17);
        introScreens.put("introScreenOne", stringManipulator.removeLastNewLine(tempIntroScreenOne));
        String tempIntroScreenOneAlpha = fileManipulator.readFile("./Screens/Intro/intro1.txt", 18, 34);
        mainMenuScreenOne = stringManipulator.removeLastNewLine(tempIntroScreenOneAlpha);
        String tempIntroScreenTwo = fileManipulator.readFile("./Screens/Intro/intro2.txt", 0, 17);
        mainMenuScreenTwo = stringManipulator.removeLastNewLine(tempIntroScreenTwo);
        String tempIntroScreenTwoAlpha = fileManipulator.readFile("./Screens/Intro/intro2.txt", 18, 34);
        mainMenuScreenTwo = stringManipulator.removeLastNewLine(tempIntroScreenTwoAlpha);
        String tempIntroScreenThree = fileManipulator.readFile("./Screens/Intro/intro3.txt", 0, 17);
        invalidInputScreen = stringManipulator.removeLastNewLine(tempIntroScreenThree);
        String tempIntroScreenThreeAlpha = fileManipulator.readFile("./Screens/Intro/intro3.txt", 18, 34);
        invalidInputScreen = stringManipulator.removeLastNewLine(tempIntroScreenThreeAlpha);
    }
}
