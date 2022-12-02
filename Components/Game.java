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
    private static String turnScreen;
    private static String ouchScreen;
    private static String goodJobScreen;

    public Game() {
        makeFileManipulator();
        makeStringManipulator();
        makeTimeManipulator();
        makeCountdownScreens();
        makeScreens();
    }

    public boolean processGame(Scanner systemScanner, String sequence, String animal, String filePath) {
        makeAnimalScreens(filePath);
        countdown();
        return playGame(systemScanner, sequence, animal);
    }

    private boolean playGame(Scanner systemScanner, String sequence, String animal) {
        int lives = 3;
        int count = 0;
        String answer1 = "";
        String answer2 = "";
        for (int i = 0; i < sequence.length(); i++) {
            if (lives < 1) {
                return false;
            }
            String step = "" + sequence.charAt(i);
            if (step.equals("w")) {
                answer1 += "w";
                answer2 += "i";
                printHearts(lives);
                System.out.println(animalScreens.get("up"));
                i++;
                String time = "" + sequence.charAt(i);
                pauseTimer(time,lives);
                count = count+2;
            } else if (step.equals("s")) {
                answer1 += "s";
                answer2 += "k";
                printHearts(lives);
                System.out.println(animalScreens.get("down"));
                i++;
                String time = "" + sequence.charAt(i);
                pauseTimer(time,lives);
                count = count+2;
            } else if (step.equals("a")) {
                answer1 += "a";
                answer2 += "j";
                printHearts(lives);
                System.out.println(animalScreens.get("left"));
                i++;
                String time = "" + sequence.charAt(i);
                pauseTimer(time,lives);
                count = count+2;
            } else if (step.equals("d")) {
                answer1 += "d";
                answer2 += "l";
                printHearts(lives);
                System.out.println(animalScreens.get("right"));
                i++;
                String time = "" + sequence.charAt(i);
                pauseTimer(time,lives);
                count = count+2;
            } else {
                printHearts(lives);
                System.out.println(turnScreen);
                String line = systemScanner.nextLine();
                if (!line.equalsIgnoreCase(answer1) && !line.equalsIgnoreCase(answer2)) {
                    for (int k = 0; k < 2; k++) {
                        printHearts(lives);
                        System.out.println(ouchScreen);
                        timeManipulator.sleepMilliseconds(200);
                        printHearts(lives-1);
                        System.out.println(ouchScreen);
                        timeManipulator.sleepMilliseconds(200);
                    }
                    --lives;
                    i -= count + 1;
                } else {
                    printHearts(lives);
                    System.out.println(goodJobScreen);
                    timeManipulator.sleepMilliseconds(800);
                }
                answer1 = "";
                answer2 = "";
                count = 0;
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

    private void pauseTimer(String note, int lives) {
        if (note.equals("1")) {
            timeManipulator.sleepMilliseconds(500);
            printBlank(lives);
            timeManipulator.sleepMilliseconds(100);
        } else if (note.equals("2")) {
            timeManipulator.sleepMilliseconds(200);
            printBlank(lives);
            timeManipulator.sleepMilliseconds(100);
        }
    }

    private void printHearts(int lives) {
        System.out.print("Lives: ");
        for (int j = 0; j < lives; j++) {
            System.out.print("♥️  ");
        }
        System.out.println();
    }
    private void printBlank(int lives) {
        System.out.print("Lives: ");
        for (int j = 0; j < lives; j++) {
            System.out.print("♥️  ");
        }
        System.out.println();
        System.out.println(blankScreen);
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
    private void makeScreens() {
        String tempBlankScreen = fileManipulator.readFile("./Screens/Game/blank.txt", 0, 16);
        blankScreen = stringManipulator.removeLastNewLine(tempBlankScreen);
        String tempTurnScreen = fileManipulator.readFile("./Screens/Game/turn.txt", 0, 16);
        turnScreen = stringManipulator.removeLastNewLine(tempTurnScreen);
        String tempOuchScreen = fileManipulator.readFile("./Screens/Game/ouch.txt", 0, 16);
        ouchScreen = stringManipulator.removeLastNewLine(tempOuchScreen);
        String tempGoodJobScreen = fileManipulator.readFile("./Screens/Game/goodJob.txt", 0, 16);
        goodJobScreen = stringManipulator.removeLastNewLine(tempGoodJobScreen);
    }
}
