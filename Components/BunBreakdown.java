package Components;

import java.util.Scanner;

public class BunBreakdown {
    private static Scanner systemScanner = null;
    private static StartingScreen startingScreen = null;
    private static MainMenu mainMenu = null;
    private static GameHandler gameHandler = null;

    public BunBreakdown() {
        systemScanner = makeScanner();
        startingScreen = makeStartingScreen();
        mainMenu = makeMainMenu();
        gameHandler = makeGameHandler();
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
                close();
            }
        }
    }

    private void close() {
        systemScanner.close();
        System.exit(0);
    }

    private MainMenu makeMainMenu() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }
    private StartingScreen makeStartingScreen() {
        if (startingScreen == null) {
            startingScreen = new StartingScreen();
        }
        return startingScreen;
    }
    private Scanner makeScanner() {
        if (systemScanner == null) {
            systemScanner = new Scanner(System.in);
        }
        return systemScanner;
    }
    private GameHandler makeGameHandler() {
        if (gameHandler == null) {
            gameHandler = new GameHandler();
        }
        return gameHandler;
    }
}
