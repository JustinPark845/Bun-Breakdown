package Components;

import java.util.Scanner;

public class BunBreakdown {
    private static Scanner systemScanner;
    private static StartingScreen startingScreen;
    private static MainMenu mainMenu;
    private static GameHandler gameHandler;

    public BunBreakdown() {
        systemScanner = new Scanner(System.in);
        gameHandler = new GameHandler();
        startingScreen = new StartingScreen();
        mainMenu = new MainMenu();
    }

    public void setUp() {
        startingScreen.processStartingScreen(systemScanner);
    }

    public void play() {
        while (true) {
            String option = mainMenu.processMainMenu(systemScanner);
            if ((option.equalsIgnoreCase("Start")) || option.equalsIgnoreCase("Level Selection")) {
                gameHandler.processGameHandler(systemScanner, option);
            } else {
                break;
            }
        }
    }

    public void close() {
        systemScanner.close();
        System.exit(0);
    }
}
