package Components;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import Functions.FileManipulator;
import Functions.StringManipulator;
import Functions.TimeManipulator;

public class GameHandler {
    private static Game game = null;
    private static FileManipulator fileManipulator = null;
    private static StringManipulator stringManipulator;
    private static TimeManipulator timeManipulator = null;
    private static Hashtable<String, String> introScreens = new Hashtable<String, String>();
    private static List<String> introScreenOrder1 = new ArrayList<String>();
    private static List<String> introScreenOrder2 = new ArrayList<String>();
    private static List<String> levelSelectionScreen = new ArrayList<String>();
    private static Hashtable<String, Hashtable<String, String>> levels = new Hashtable<String, Hashtable<String, String>>();
    private static List<String> resultScreens = new ArrayList<String>();
    private static String levelClearScreen = null;
    private static String levelOneScreen = null;
    private static String levelTwoScreen = null;
    private static String levelThreeScreen = null;

    public GameHandler() {
        makeFileManipulator();
        makeStringManipulator();
        makeTimeManipulator();
        makeIntroScreens();
        makeLevelSelectionScreen();
        makeScreens();
        makeLevels();
        makeGame();
    }

    public void processGameHandler(Scanner systemScanner, String option) {
        if (option.equalsIgnoreCase("Start")) {
            playIntro();
            // Level 1
            System.out.println(levelOneScreen);
            systemScanner.nextLine();
            while (true) {
                if (game.processGame(systemScanner, levels.get("1").get("sequence"), levels.get("1").get("animal"), levels.get("1").get("file"))) {
                    System.out.println(levelClearScreen);
                    systemScanner.nextLine();
                    // Level 2
                    System.out.println(levelTwoScreen);
                    systemScanner.nextLine();
                    while (true) {
                        if (game.processGame(systemScanner, levels.get("2").get("sequence"), levels.get("2").get("animal"), levels.get("2").get("file"))) {
                            System.out.println(levelClearScreen);
                            systemScanner.nextLine();
                            // Level 3
                            System.out.println(levelThreeScreen);
                            systemScanner.nextLine();
                            while (true) {
                                if (game.processGame(systemScanner, levels.get("3").get("sequence"), levels.get("3").get("animal"), levels.get("3").get("file"))) {
                                    for (int i = 0; i < 6; i++) {
                                        System.out.println(resultScreens.get(0));
                                        timeManipulator.sleepMilliseconds(500);
                                        System.out.println(resultScreens.get(1));
                                        timeManipulator.sleepMilliseconds(500);
                                    }
                                    System.out.println(resultScreens.get(2));
                                    systemScanner.nextLine();
                                    break;
                                } else {
                                    System.out.println(resultScreens.get(3));
                                    if (!"Continue".equalsIgnoreCase(systemScanner.nextLine())) {
                                        break;
                                    }
                                }
                            }
                            break;
                        } else {
                            System.out.println(resultScreens.get(3));
                            if (!"Continue".equalsIgnoreCase(systemScanner.nextLine())) {
                                break;
                            }
                        }
                    }
                    break;
                } else {
                    System.out.println(resultScreens.get(3));
                    if (!"Continue".equalsIgnoreCase(systemScanner.nextLine())) {
                        break;
                    }
                }
            }
        } else {
            System.out.println(levelSelectionScreen.get(0));
            String level = systemScanner.nextLine();
            if (level.equalsIgnoreCase("1") && !level.equalsIgnoreCase("2") && !level.equalsIgnoreCase("3")) {
                if (level.equals("1")) {
                    // Level 1
                    System.out.println(levelOneScreen);
                    systemScanner.nextLine();
                    while (true) {
                        if (game.processGame(systemScanner, levels.get("1").get("sequence"), levels.get("1").get("animal"), levels.get("1").get("file"))) {
                            System.out.println(levelClearScreen);
                            systemScanner.nextLine();
                            break;
                        } else {
                            System.out.println(resultScreens.get(3));
                            if (!"Continue".equalsIgnoreCase(systemScanner.nextLine())) {
                                break;
                            }
                        }
                    }
                } else if (level.equals("2")) {
                    // Level 2
                    System.out.println(levelTwoScreen);
                    systemScanner.nextLine();
                    while (true) {
                        if (game.processGame(systemScanner, levels.get("2").get("sequence"), levels.get("2").get("animal"), levels.get("2").get("file"))) {
                            System.out.println(levelClearScreen);
                            systemScanner.nextLine();
                            break;
                        } else {
                            System.out.println(resultScreens.get(3));
                            if (!"Continue".equalsIgnoreCase(systemScanner.nextLine())) {
                                break;
                            }
                        }
                    }            
                } else if (level.equals("3")) {
                    // Level 3
                    System.out.println(levelThreeScreen);
                    systemScanner.nextLine();
                    while (true) {
                        if (game.processGame(systemScanner, levels.get("3").get("sequence"), levels.get("3").get("animal"), levels.get("3").get("file"))) {
                            System.out.println(levelClearScreen);
                            systemScanner.nextLine();
                            break;
                        } else {
                            System.out.println(resultScreens.get(3));
                            if (!"Continue".equalsIgnoreCase(systemScanner.nextLine())) {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void makeLevels() {
        // w = up, s = down, a = left, d = right, 1 = 1 second, 2 = 1/2 second, / = next round
        // Level 1
        String level1sequence = "w1w1w1/w1w1w2w2w2w2/w1w1w1s1/w2w2w2w2s2s2s2s2/w1w1s2s2w2w2/w2s2w1s2w2s1/s2s2s2s2w2s2w2s2/w1w1w1w1s1s1s1s1w1w1w1w1a1d1/";
        String level1animal = "cat";
        String level1animalfile = "./Screens/Game/Animals/cat.txt";
        levels.put("1",new Hashtable<String, String>(){{put("sequence",level1sequence);put("animal",level1animal);put("file",level1animalfile);}});
        // Level 2
        String level2sequence = "w1a1s1d1/w1d1s1a1/a1a2a2d1d2d2/w2w2a2a2s2s2d2d2/w2a2s2d2w2a2s2d2/s2s2s1w2w2w1d2d2d1a2a2a1/w1s1d1a1w2s2d2a2/d2d2d2d2d2d2d2a2d2a2a2a2a2a2a2a2/";
        String level2animal = "bird";
        String level2animalfile = "./Screens/Game/Animals/bird.txt";
        levels.put("2",new Hashtable<String, String>(){{put("sequence",level2sequence);put("animal",level2animal);put("file",level2animalfile);}});
        // Level 3
        String level3sequence = "w2w1w2s2s1s2d2d1d2a2a1a2/d2a2d2a2s1w1s1/w2d1a2s1d2w1s2w1/d1a1d2d2a2s1w1s2/d1s1a1w2d2s2/w2d2s2a2w2d2s2a2s2d2w2a2s2d2w2a2/w2s1d2a2w1s1d2s2a1d2w2s2s2w2/";
        String level3animal = "buffbunny";
        String level3animalfile = "./Screens/Game/Animals/buffBunny.txt";
        levels.put("3",new Hashtable<String, String>(){{put("sequence",level3sequence);put("animal",level3animal);put("file",level3animalfile);}});
    }

    private void playIntro() {
        for (int i = 0; i < 2; i++) {
            System.out.println(introScreens.get(introScreenOrder1.get(0)));
            timeManipulator.sleepMilliseconds(500);
            System.out.println(introScreens.get(introScreenOrder1.get(1)));
            timeManipulator.sleepMilliseconds(500);
        }
        for (int i = 0; i < introScreenOrder1.size(); i++) {
            System.out.println(introScreens.get(introScreenOrder1.get(i)));
            timeManipulator.sleepMilliseconds(500);
        }
        timeManipulator.sleepMilliseconds(500);
        for (int i = 0; i < 10; i++) {
            System.out.println(introScreens.get(introScreenOrder2.get(0)));
            timeManipulator.sleepMilliseconds(100);
            System.out.println(introScreens.get(introScreenOrder2.get(1)));
            timeManipulator.sleepMilliseconds(100);
        }
        timeManipulator.sleepMilliseconds(500);
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
        introScreenOrder1.add("introScreenOne");
        String tempIntroScreenOneAlpha = fileManipulator.readFile("./Screens/Intro/intro1.txt", 18, 34);
        introScreens.put("introScreenOneAlpha", stringManipulator.removeLastNewLine(tempIntroScreenOneAlpha));
        introScreenOrder1.add("introScreenOneAlpha");
        String tempIntroScreenTwo = fileManipulator.readFile("./Screens/Intro/intro2.txt", 0, 17);
        introScreens.put("introScreenTwo", stringManipulator.removeLastNewLine(tempIntroScreenTwo));
        introScreenOrder1.add("introScreenTwo");
        String tempIntroScreenTwoAlpha = fileManipulator.readFile("./Screens/Intro/intro2.txt", 18, 34);
        introScreens.put("introScreenTwoAlpha", stringManipulator.removeLastNewLine(tempIntroScreenTwoAlpha));
        introScreenOrder1.add("introScreenTwoAlpha");
        String tempIntroScreenThree = fileManipulator.readFile("./Screens/Intro/intro3.txt", 0, 17);
        introScreens.put("introScreenThree", stringManipulator.removeLastNewLine(tempIntroScreenThree));
        introScreenOrder1.add("introScreenThree");
        String tempIntroScreenThreeAlpha = fileManipulator.readFile("./Screens/Intro/intro3.txt", 18, 34);
        introScreens.put("introScreenThreeAlpha", stringManipulator.removeLastNewLine(tempIntroScreenThreeAlpha));
        introScreenOrder1.add("introScreenThreeAlpha");
        String tempIntroScreenFour = fileManipulator.readFile("./Screens/Intro/intro4.txt", 0, 17);
        introScreens.put("introScreenFour", stringManipulator.removeLastNewLine(tempIntroScreenFour));
        introScreenOrder1.add("introScreenFour");
        String tempIntroScreenFourAlpha = fileManipulator.readFile("./Screens/Intro/intro4.txt", 18, 34);
        introScreens.put("introScreenFourAlpha", stringManipulator.removeLastNewLine(tempIntroScreenFourAlpha));
        introScreenOrder1.add("introScreenFourAlpha");
        String tempIntroScreenFive = fileManipulator.readFile("./Screens/Intro/intro5.txt", 0, 17);
        introScreens.put("introScreenFive", stringManipulator.removeLastNewLine(tempIntroScreenFive));
        introScreenOrder1.add("introScreenFive");
        String tempIntroScreenFiveAlpha = fileManipulator.readFile("./Screens/Intro/intro5.txt", 18, 34);
        introScreens.put("introScreenFiveAlpha", stringManipulator.removeLastNewLine(tempIntroScreenFiveAlpha));
        introScreenOrder1.add("introScreenFiveAlpha");
        String tempIntroScreenDanceOff = fileManipulator.readFile("./Screens/Intro/danceoff.txt", 0, 17);
        introScreens.put("introScreenDanceOff", stringManipulator.removeLastNewLine(tempIntroScreenDanceOff));
        introScreenOrder2.add("introScreenDanceOff");
        String tempIntroScreenDanceOffAlpha = fileManipulator.readFile("./Screens/Intro/danceoff.txt", 18, 34);
        introScreens.put("introScreenDanceOffAlpha", stringManipulator.removeLastNewLine(tempIntroScreenDanceOffAlpha));
        introScreenOrder2.add("introScreenDanceOffAlpha");
    }
    private void makeLevelSelectionScreen() {
        String tempLevelSelectionScreen = fileManipulator.readFile("./Screens/Menu/levelSelection.txt", 0, 17);
        levelSelectionScreen.add(0, stringManipulator.removeLastNewLine(tempLevelSelectionScreen));
        String tempLevelSelectionScreenInvalid = fileManipulator.readFile("./Screens/Menu/levelSelection.txt", 18, 34);
        levelSelectionScreen.add(1, stringManipulator.removeLastNewLine(tempLevelSelectionScreenInvalid));
    }
    private void makeScreens() {
        String tempVictoryScreen = fileManipulator.readFile("./Screens/Game/victory.txt", 0, 17);
        resultScreens.add(0, stringManipulator.removeLastNewLine(tempVictoryScreen));
        String tempVictoryScreenAlpha = fileManipulator.readFile("./Screens/Game/victory.txt", 18, 34);
        resultScreens.add(1, stringManipulator.removeLastNewLine(tempVictoryScreenAlpha));
        String tempVictoryScreenReturn = fileManipulator.readFile("./Screens/Game/victory.txt", 35, 51);
        resultScreens.add(2, stringManipulator.removeLastNewLine(tempVictoryScreenReturn));
        String tempDefeatScreen = fileManipulator.readFile("./Screens/Game/defeat.txt", 0, 17);
        resultScreens.add(3, stringManipulator.removeLastNewLine(tempDefeatScreen));
        String tempLevelClearScreen = fileManipulator.readFile("./Screens/Game/levelClear.txt", 0, 17);
        levelClearScreen = stringManipulator.removeLastNewLine(tempLevelClearScreen);
        String tempLevelOneScreen = fileManipulator.readFile("./Screens/Game/Animals/cat.txt", 65, 81);
        levelOneScreen = stringManipulator.removeLastNewLine(tempLevelOneScreen);
        String tempLevelTwoScreen = fileManipulator.readFile("./Screens/Game/Animals/bird.txt", 65, 81);
        levelTwoScreen = stringManipulator.removeLastNewLine(tempLevelTwoScreen);
        String tempLevelThreeScreen = fileManipulator.readFile("./Screens/Game/Animals/buffBunny.txt", 65, 81);
        levelThreeScreen = stringManipulator.removeLastNewLine(tempLevelThreeScreen);
    }
}
