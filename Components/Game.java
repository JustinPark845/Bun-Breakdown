package Components;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import Functions.FileManipulator;
import Functions.Manipulator;
import Functions.StringManipulator;
import Functions.TimeManipulator;

public class Game {
    private static Manipulator fileManipulator;
    private static Manipulator stringManipulator;
    private static Manipulator timeManipulator;
    private static ArrayList<String> moves = new ArrayList<String>();
    private static List<String> countdownScreens = new ArrayList<String>();
    private static Hashtable<String, String> animalScreens = new Hashtable<String, String>();
    private static String blankScreen;
    private static String turnScreen;
    private static String ouchScreen;
    private static String goodJobScreen;

    public Game() {
        ManipulatorMaker makeFileManipulator = new FileManipulatorMaker();
        fileManipulator = makeFileManipulator.makeManipulator();
        ManipulatorMaker makeStringManipulator = new StringManipulatorMaker();
        stringManipulator = makeStringManipulator.makeManipulator();
        ManipulatorMaker makeTimeManipulator = new TimeManipulatorMaker();
        timeManipulator = makeTimeManipulator.makeManipulator();
        makeCountdownScreens();
        makeScreens();
    }

    public boolean processGame(Scanner systemScanner, String sequence, String animal, String filePath) {
        makeAnimalScreens(filePath);
        makeMoves(sequence);
        countdown();
        return playGame(systemScanner, animal, iterator());
    }

    private boolean playGame(Scanner systemScanner, String animal, Iterator<String> iter) {
        // Initial Start Blank
        printHearts(3);
        System.out.println(blankScreen);
        ((TimeManipulator) timeManipulator).sleepMilliseconds(1000);

        // Game Initialization
        int lives = 3;
        int count = 0;
        String answer1 = "";
        String answer2 = "";
        while (iter.hasNext()) {
            if (lives < 1) {
                return false;
            }
            String step = "" + iter.next();
            if (step.equals("w")) {
                answer1 += "w";
                answer2 += "i";
                printHearts(lives);
                System.out.println(animalScreens.get("up"));
                String time = "" + iter.next();
                pauseTimer(time,lives);
                count = count+2;
            } else if (step.equals("s")) {
                answer1 += "s";
                answer2 += "k";
                printHearts(lives);
                System.out.println(animalScreens.get("down"));
                String time = "" + iter.next();
                pauseTimer(time,lives);
                count = count+2;
            } else if (step.equals("a")) {
                answer1 += "a";
                answer2 += "j";
                printHearts(lives);
                System.out.println(animalScreens.get("left"));
                String time = "" + iter.next();
                pauseTimer(time,lives);
                count = count+2;
            } else if (step.equals("d")) {
                answer1 += "d";
                answer2 += "l";
                printHearts(lives);
                System.out.println(animalScreens.get("right"));
                String time = "" + iter.next();
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
                        ((TimeManipulator) timeManipulator).sleepMilliseconds(200);
                        printHearts(lives-1);
                        System.out.println(ouchScreen);
                        ((TimeManipulator) timeManipulator).sleepMilliseconds(200);
                    }
                    --lives;
                    iter.goPrevious(count + 1);
                } else {
                    printHearts(lives);
                    System.out.println(goodJobScreen);
                    ((TimeManipulator) timeManipulator).sleepMilliseconds(800);
                    printHearts(lives);
                    System.out.println(blankScreen);
                    ((TimeManipulator) timeManipulator).sleepMilliseconds(500);
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
            ((TimeManipulator) timeManipulator).sleepMilliseconds(1000);
        }
    }

    private void pauseTimer(String note, int lives) {
        if (note.equals("1")) {
            ((TimeManipulator) timeManipulator).sleepMilliseconds(700);
            printBlank(lives);
            ((TimeManipulator) timeManipulator).sleepMilliseconds(200);
        } else if (note.equals("2")) {
            ((TimeManipulator) timeManipulator).sleepMilliseconds(300);
            printBlank(lives);
            ((TimeManipulator) timeManipulator).sleepMilliseconds(100);
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

    private void makeMoves(String sequence) {
        moves.clear();
        for (int i = 0; i < sequence.length(); i++) {
            moves.add(""+sequence.charAt(i));
        }
    }

    public Iterator<String> iterator()
	{
		return new GameIterator();
	}

	private class GameIterator implements Iterator<String> {
		private int count;

		public GameIterator() {
			count = 0;
		}
		public boolean hasNext() {
			return count < moves.size();
		}
		public String next() {
            return moves.get(count++);
		}

        public void goPrevious(int i) {
            count -= i;
        }
	}

    private void makeCountdownScreens() {
        String tempCountdownOne = ((FileManipulator) fileManipulator).readFile("./Screens/Game/countdown.txt", 0, 17);
        countdownScreens.add(((StringManipulator) stringManipulator).removeLastNewLine(tempCountdownOne));
        String tempCountdownTwo = ((FileManipulator) fileManipulator).readFile("./Screens/Game/countdown.txt", 18, 34);
        countdownScreens.add(((StringManipulator) stringManipulator).removeLastNewLine(tempCountdownTwo));
        String tempCountdownThree = ((FileManipulator) fileManipulator).readFile("./Screens/Game/countdown.txt", 35, 51);
        countdownScreens.add(((StringManipulator) stringManipulator).removeLastNewLine(tempCountdownThree));
    }
    private void makeAnimalScreens(String filePath) {
        String tempAnimalScreenUp = ((FileManipulator) fileManipulator).readFile(filePath, 0, 16);
        animalScreens.put("up",((StringManipulator) stringManipulator).removeLastNewLine(tempAnimalScreenUp));
        String tempAnimalScreenDown = ((FileManipulator) fileManipulator).readFile(filePath, 17, 32);
        animalScreens.put("down",((StringManipulator) stringManipulator).removeLastNewLine(tempAnimalScreenDown));
        String tempAnimalScreenLeft = ((FileManipulator) fileManipulator).readFile(filePath, 33, 48);
        animalScreens.put("left",((StringManipulator) stringManipulator).removeLastNewLine(tempAnimalScreenLeft));
        String tempAnimalScreenRight = ((FileManipulator) fileManipulator).readFile(filePath, 49, 64);
        animalScreens.put("right",((StringManipulator) stringManipulator).removeLastNewLine(tempAnimalScreenRight));
    }
    private void makeScreens() {
        String tempBlankScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Game/blank.txt", 0, 16);
        blankScreen = ((StringManipulator) stringManipulator).removeLastNewLine(tempBlankScreen);
        String tempTurnScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Game/turn.txt", 0, 16);
        turnScreen = ((StringManipulator) stringManipulator).removeLastNewLine(tempTurnScreen);
        String tempOuchScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Game/ouch.txt", 0, 16);
        ouchScreen = ((StringManipulator) stringManipulator).removeLastNewLine(tempOuchScreen);
        String tempGoodJobScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Game/goodJob.txt", 0, 16);
        goodJobScreen = ((StringManipulator) stringManipulator).removeLastNewLine(tempGoodJobScreen);
    }
}
