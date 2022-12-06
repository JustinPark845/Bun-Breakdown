package Components;
import java.util.Scanner; // Import the Scanner class to read text files

import Functions.FileManipulator;
import Functions.Manipulator;
import Functions.StringManipulator;
import Functions.TimeManipulator;

public class MainMenu {
    private static Manipulator fileManipulator;
    private static Manipulator stringManipulator;
    private static Manipulator timeManipulator;
    private static String mainMenuScreenOne;
    private static String mainMenuScreenTwo;
    private static String invalidInputScreen;
    private static String tutorialScreen;

    public MainMenu() {
        ManipulatorMaker makeFileManipulator = new FileManipulatorMaker();
        fileManipulator = makeFileManipulator.makeManipulator();
        ManipulatorMaker makeStringManipulator = new StringManipulatorMaker();
        stringManipulator = makeStringManipulator.makeManipulator();
        ManipulatorMaker makeTimeManipulator = new TimeManipulatorMaker();
        timeManipulator = makeTimeManipulator.makeManipulator();
        makeMainMenuScreens();
    }

    public String processMainMenu(Scanner systemScanner) {
        String line = "";
        printMainMenu(false);
        line = systemScanner.nextLine();
        while(!line.equalsIgnoreCase("Start") && !line.equalsIgnoreCase("Level Selection") && !line.equalsIgnoreCase("Quit")) {
            if (line.equalsIgnoreCase("Wiggle Ears")) {
                printMainMenu(false);
            } else if (line.equalsIgnoreCase("Tutorial")) {
                System.out.println(tutorialScreen);
                systemScanner.nextLine();
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
                ((TimeManipulator) timeManipulator).sleepMilliseconds(100);
                System.out.println(mainMenuScreenTwo);
                ((TimeManipulator) timeManipulator).sleepMilliseconds(100);
                System.out.println(mainMenuScreenOne);
            }
        }
    }
    private void makeMainMenuScreens() {
        String tempMainMenuScreenOne = ((FileManipulator) fileManipulator).readFile("./Screens/Menu/mainMenu.txt", 0, 17);
        mainMenuScreenOne = ((StringManipulator) stringManipulator).removeLastNewLine(tempMainMenuScreenOne);
        String tempMainMenuScreenTwo = ((FileManipulator) fileManipulator).readFile("./Screens/Menu/mainMenu.txt", 18, 34);
        mainMenuScreenTwo = ((StringManipulator) stringManipulator).removeLastNewLine( tempMainMenuScreenTwo);
        String tempInvalidInputScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Menu/mainMenu.txt", 35, 51);
        invalidInputScreen = ((StringManipulator) stringManipulator).removeLastNewLine(tempInvalidInputScreen);
        String tempTutorialScreen = ((FileManipulator) fileManipulator).readFile("./Screens/Menu/tutorial.txt", 0, 17);
        tutorialScreen = ((StringManipulator) stringManipulator).removeLastNewLine(tempTutorialScreen);
    }
}
