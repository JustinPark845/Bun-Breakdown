package Components;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import Functions.FileManipulator;
import Functions.StringManipulator;
import Functions.TimeManipulator;

public class Game {
    private static FileManipulator fileManipulator = null;
    private static StringManipulator stringManipulator;
    private static TimeManipulator timeManipulator = null;
    private static List<String> countdownScreens = new ArrayList<String>();
    private static Hashtable<String, String> animalScreens = new Hashtable<String, String>();
    private static String blankScreen;

    public Game() {
        makeFileManipulator();
        makeStringManipulator();
        makeTimeManipulator();
        makeCountdownScreens();
        makeBlankScreen();
    }

    public boolean processGame(Scanner systemScanner, String sequence, String animal, String filePath) {
        makeAnimalScreens(filePath);
        countdown();
        return playGame(systemScanner, sequence, animal);
    }

    private boolean playGame(Scanner systemScanner, String sequence, String animal) {
        int lives = 3;
        String answer = "";
        for (int i = 0; i < sequence.length(); i++) {
            if (lives < 1) {
                return false;
            }
            System.out.print("Lives: ");
            for (int j = 0; j < lives; j++) {
                System.out.print("♥️  ");
            }
            System.out.println();
            System.out.println("here");
            String step = "" + sequence.charAt(i);
            if (step.equals("w")) {
                answer += step;
                System.out.println(animalScreens.get("up"));
                timeManipulator.sleepMilliseconds(400);
                System.out.println(blankScreen);
                timeManipulator.sleepMilliseconds(100);
            } else if (step.equals("s")) {
                answer += step;
                System.out.println(animalScreens.get("down"));
                timeManipulator.sleepMilliseconds(400);
                System.out.println(blankScreen);
                timeManipulator.sleepMilliseconds(100);
            } else if (step.equals("a")) {
                answer += step;
                System.out.println(animalScreens.get("left"));
                timeManipulator.sleepMilliseconds(400);
                System.out.println(blankScreen);
                timeManipulator.sleepMilliseconds(100);
            } else if (step.equals("d")) {
                answer += step;
                System.out.println(animalScreens.get("right"));
                timeManipulator.sleepMilliseconds(400);
                System.out.println(blankScreen);
                timeManipulator.sleepMilliseconds(100);
            } else if (step.equals(" ")) {
                timeManipulator.sleepMilliseconds(400);
                System.out.println(blankScreen);
                timeManipulator.sleepMilliseconds(100);
            } else {
                String line = systemScanner.nextLine();
                if (!line.equalsIgnoreCase(answer)) {
                    --lives;
                }
                answer = "";
            }
        }
        return true;
    }

    private void countdown() {
        for (int i = 0; i < countdownScreens.size(); i++) {
            System.out.println(countdownScreens.get(i));
            timeManipulator.sleepMilliseconds(1000);
        }
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
    private void makeCountdownScreens() {
        String tempCountdownOne = fileManipulator.readFile("./Screens/Game/countdown.txt", 0, 17);
        countdownScreens.add(stringManipulator.removeLastNewLine(tempCountdownOne));
        String tempCountdownTwo = fileManipulator.readFile("./Screens/Game/countdown.txt", 18, 34);
        countdownScreens.add(stringManipulator.removeLastNewLine(tempCountdownTwo));
        String tempCountdownThree = fileManipulator.readFile("./Screens/Game/countdown.txt", 35, 51);
        countdownScreens.add(stringManipulator.removeLastNewLine(tempCountdownThree));
    }
    private void makeAnimalScreens(String filePath) {
        String tempAnimalScreenUp = fileManipulator.readFile(filePath, 0, 16);
        animalScreens.put("up",stringManipulator.removeLastNewLine(tempAnimalScreenUp));
        String tempAnimalScreenDown = fileManipulator.readFile(filePath, 17, 32);
        animalScreens.put("down",stringManipulator.removeLastNewLine(tempAnimalScreenDown));
        String tempAnimalScreenLeft = fileManipulator.readFile(filePath, 33, 48);
        animalScreens.put("left",stringManipulator.removeLastNewLine(tempAnimalScreenLeft));
        String tempAnimalScreenRight = fileManipulator.readFile(filePath, 49, 64);
        animalScreens.put("right",stringManipulator.removeLastNewLine(tempAnimalScreenRight));
    }
    private void makeBlankScreen() {
        String tempBlankScreen = fileManipulator.readFile("./Screens/Game/blank.txt", 0, 16);
        blankScreen = stringManipulator.removeLastNewLine(tempBlankScreen);
    }
}
