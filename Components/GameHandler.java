package Components;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import Functions.FileManipulator;
import Functions.Manipulator;
import Functions.StringManipulator;
import Functions.TimeManipulator;

public class GameHandler {
    private static Game game = null;
    private static Manipulator fileManipulator;
    private static Manipulator stringManipulator;
    private static Manipulator timeManipulator;
    private static Hashtable<String, String> introScreens = new Hashtable<String, String>();
    private static List<String> introScreenOrder1 = new ArrayList<String>();
    private static List<String> introScreenOrder2 = new ArrayList<String>();
    private static List<String> levelSelectionScreen = new ArrayList<String>();
    private static Hashtable<String, Hashtable<String, String>> levels = new Hashtable<String, Hashtable<String, String>>();
    private static List<String> resultScreens = new ArrayList<String>();
    private static String levelClearScreen;
    private static String levelOneScreen;
    private static String levelTwoScreen;
    private static String levelThreeScreen;

    public GameHandler() {
        ManipulatorMaker makeFileManipulator = new FileManipulatorMaker();
        fileManipulator = makeFileManipulator.makeManipulator();
        ManipulatorMaker makeStringManipulator = new StringManipulatorMaker();
        stringManipulator = makeStringManipulator.makeManipulator();
        ManipulatorMaker makeTimeManipulator = new TimeManipulatorMaker();
        timeManipulator = makeTimeManipulator.makeManipulator();
        makeGame();
        makeIntroScreens();
        makeLevelSelectionScreen();
        makeScreens();
        makeLevels();
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
                                        ((TimeManipulator) timeManipulator).sleepMilliseconds(500);
                                        System.out.println(resultScreens.get(1));
                                        ((TimeManipulator) timeManipulator).sleepMilliseconds(500);
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
            if (level.equalsIgnoreCase("1") || level.equalsIgnoreCase("2") || level.equalsIgnoreCase("3")) {
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

    private Game makeGame() {
        if (game == null) {
            game = new Game();
        }
        return game;
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
        String level3sequence = "w2w1w2s2s1s2d2d1d2a2a1a2/d2a2d2a2s1w1s1/w2d1a2s1d2w1s2w1/d1a1d2d2a2s1w1s2/d1s1a1w2d2s2/w2d2s2a2w2d2s2a2s2d2w2a2s2d2w2a2/w1s1a1a1d1d1d1d1w1s1/";
        String level3animal = "buffbunny";
        String level3animalfile = "./Screens/Game/Animals/buffBunny.txt";
        levels.put("3",new Hashtable<String, String>(){{put("sequence",level3sequence);put("animal",level3animal);put("file",level3animalfile);}});
    }

    private void playIntro() {
        for (int i = 0; i < 2; i++) {
            System.out.println(introScreens.get(introScreenOrder1.get(0)));
            ((TimeManipulator) timeManipulator).sleepMilliseconds(500);
            System.out.println(introScreens.get(introScreenOrder1.get(1)));
            ((TimeManipulator) timeManipulator).sleepMilliseconds(500);
        }
        for (int i = 0; i < introScreenOrder1.size(); i++) {
            System.out.println(introScreens.get(introScreenOrder1.get(i)));
            ((TimeManipulator) timeManipulator).sleepMilliseconds(500);
        }
        ((TimeManipulator) timeManipulator).sleepMilliseconds(500);
        for (int i = 0; i < 10; i++) {
            System.out.println(introScreens.get(introScreenOrder2.get(0)));
            ((TimeManipulator) timeManipulator).sleepMilliseconds(100);
            System.out.println(introScreens.get(introScreenOrder2.get(1)));
            ((TimeManipulator) timeManipulator).sleepMilliseconds(100);
        }
        ((TimeManipulator) timeManipulator).sleepMilliseconds(500);
    }

    private void makeIntroScreens() {
        String tempIntroScreenOne = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/intro1.txt", 0, 17);
        introScreens.put("introScreenOne", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenOne));
        introScreenOrder1.add("introScreenOne");
        String tempIntroScreenOneAlpha = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/intro1.txt", 18, 34);
        introScreens.put("introScreenOneAlpha", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenOneAlpha));
        introScreenOrder1.add("introScreenOneAlpha");
        String tempIntroScreenTwo = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/intro2.txt", 0, 17);
        introScreens.put("introScreenTwo", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenTwo));
        introScreenOrder1.add("introScreenTwo");
        String tempIntroScreenTwoAlpha = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/intro2.txt", 18, 34);
        introScreens.put("introScreenTwoAlpha", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenTwoAlpha));
        introScreenOrder1.add("introScreenTwoAlpha");
        String tempIntroScreenThree = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/intro3.txt", 0, 17);
        introScreens.put("introScreenThree", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenThree));
        introScreenOrder1.add("introScreenThree");
        String tempIntroScreenThreeAlpha = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/intro3.txt", 18, 34);
        introScreens.put("introScreenThreeAlpha", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenThreeAlpha));
        introScreenOrder1.add("introScreenThreeAlpha");
        String tempIntroScreenFour = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/intro4.txt", 0, 17);
        introScreens.put("introScreenFour", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenFour));
        introScreenOrder1.add("introScreenFour");
        String tempIntroScreenFourAlpha = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/intro4.txt", 18, 34);
        introScreens.put("introScreenFourAlpha", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenFourAlpha));
        introScreenOrder1.add("introScreenFourAlpha");
        String tempIntroScreenFive = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/intro5.txt", 0, 17);
        introScreens.put("introScreenFive", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenFive));
        introScreenOrder1.add("introScreenFive");
        String tempIntroScreenFiveAlpha = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/intro5.txt", 18, 34);
        introScreens.put("introScreenFiveAlpha", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenFiveAlpha));
        introScreenOrder1.add("introScreenFiveAlpha");
        String tempIntroScreenDanceOff = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/danceoff.txt", 0, 17);
        introScreens.put("introScreenDanceOff", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenDanceOff));
        introScreenOrder2.add("introScreenDanceOff");
        String tempIntroScreenDanceOffAlpha = ((FileManipulator) fileManipulator).readFile("./Screens/Intro/danceoff.txt", 18, 34);
        introScreens.put("introScreenDanceOffAlpha", ((StringManipulator) stringManipulator).removeLastNewLine(tempIntroScreenDanceOffAlpha));
        introScreenOrder2.add("introScreenDanceOffAlpha");
    }
    private void makeLevelSelectionScreen() {
        String tempLevelSelectionScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Menu/levelSelection.txt", 0, 17);
        levelSelectionScreen.add(0, ((StringManipulator) stringManipulator).removeLastNewLine(tempLevelSelectionScreen));
        String tempLevelSelectionScreenInvalid = ((FileManipulator) fileManipulator).readFile("./Screens/Menu/levelSelection.txt", 18, 34);
        levelSelectionScreen.add(1, ((StringManipulator) stringManipulator).removeLastNewLine(tempLevelSelectionScreenInvalid));
    }
    private void makeScreens() {
        String tempVictoryScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Game/victory.txt", 0, 17);
        resultScreens.add(0, ((StringManipulator) stringManipulator).removeLastNewLine(tempVictoryScreen));
        String tempVictoryScreenAlpha = ((FileManipulator) fileManipulator).readFile("./Screens/Game/victory.txt", 18, 34);
        resultScreens.add(1, ((StringManipulator) stringManipulator).removeLastNewLine(tempVictoryScreenAlpha));
        String tempVictoryScreenReturn = ((FileManipulator) fileManipulator).readFile("./Screens/Game/victory.txt", 35, 51);
        resultScreens.add(2, ((StringManipulator) stringManipulator).removeLastNewLine(tempVictoryScreenReturn));
        String tempDefeatScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Game/defeat.txt", 0, 17);
        resultScreens.add(3, ((StringManipulator) stringManipulator).removeLastNewLine(tempDefeatScreen));
        String tempLevelClearScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Game/levelClear.txt", 0, 17);
        levelClearScreen = ((StringManipulator) stringManipulator).removeLastNewLine(tempLevelClearScreen);
        String tempLevelOneScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Game/Animals/cat.txt", 65, 81);
        levelOneScreen = ((StringManipulator) stringManipulator).removeLastNewLine(tempLevelOneScreen);
        String tempLevelTwoScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Game/Animals/bird.txt", 65, 81);
        levelTwoScreen = ((StringManipulator) stringManipulator).removeLastNewLine(tempLevelTwoScreen);
        String tempLevelThreeScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Game/Animals/buffBunny.txt", 65, 81);
        levelThreeScreen = ((StringManipulator) stringManipulator).removeLastNewLine(tempLevelThreeScreen);
    }
}
