package Components;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import Functions.StringManipulator;

public class StartingScreen {
    private static StringManipulator stringManipulator;
    private static String startingScreen;

    public StartingScreen() {
       makeStringManipulator();
       makeStartingScreen();
    }

    public boolean processStartingScreen(Scanner systemScanner) {
        String line = "";
        while(!line.equalsIgnoreCase("Start")) {
            printScreen(startingScreen);
            line = systemScanner.nextLine();
        }
        return true;
    }
    
    private void printScreen(String screen) {
      System.out.println(screen);
    }

    private void makeStringManipulator() {
      stringManipulator = new StringManipulator();
    }
    private void makeStartingScreen() {
      if (startingScreen == null) {
          try {
              File myObj = new File("./Screens/startingScreen.txt");
              Scanner myReader = new Scanner(myObj);
              String screen = "";
              while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                screen = screen + data + "\n";
              }
              myReader.close();
              startingScreen = stringManipulator.removeLastNewLine(screen);
            } catch (FileNotFoundException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
      }
  }
}
