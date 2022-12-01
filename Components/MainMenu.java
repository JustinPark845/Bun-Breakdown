package Components;
import java.util.Scanner; // Import the Scanner class to read text files

import Functions.FileManipulator;
import Functions.StringManipulator;
import Functions.TimeManipulator;

public class MainMenu {
    private static FileManipulator fileManipulator;
    private static StringManipulator stringManipulator;
    private static TimeManipulator timeManipulator;
    private static String mainMenuScreenOne;
    private static String mainMenuScreenTwo;
    private static String invalidInputScreen;

    public MainMenu() {
        makeFileManipulator();
        makeStringManipulator();
        makeTimeManipulator();
        makeMainMenuScreens();
    }

    public String processMainMenu(Scanner systemScanner) {
        String line = "";
        printMainMenu(false);
        line = systemScanner.nextLine();
        while(!line.equalsIgnoreCase("Start") && !line.equalsIgnoreCase("Level Selection") && !line.equalsIgnoreCase("Quit")) {
            if (line.equalsIgnoreCase("Wiggle Ears")) {
                printMainMenu(false);
            } else {
                printMainMenu(true);
            }
            line = systemScanner.nextLine();
        }
        return line;
    }

    private void printMainMenu(boolean invalid) {
        if (invalid) {
            System.out.println(invalidInputScreen);
        } else {
            System.out.println(mainMenuScreenOne);
            for (int i = 0; i < 2; i++) {
                timeManipulator.sleepMilliseconds(100);
                System.out.println(mainMenuScreenTwo);
                timeManipulator.sleepMilliseconds(100);
                System.out.println(mainMenuScreenOne);
            }
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
    private void makeMainMenuScreens() {
        String tempMainMenuScreenOne = fileManipulator.readFile("./Screens/mainMenu.txt", 0, 17);
        mainMenuScreenOne = stringManipulator.removeLastNewLine(tempMainMenuScreenOne);
        String tempMainMenuScreenTwo = fileManipulator.readFile("./Screens/mainMenu.txt", 18, 34);
        mainMenuScreenTwo = stringManipulator.removeLastNewLine( tempMainMenuScreenTwo);
        String tempInvalidInputScreen = fileManipulator.readFile("./Screens/mainMenu.txt", 35, 51);
        invalidInputScreen = stringManipulator.removeLastNewLine(tempInvalidInputScreen);
    }
}
