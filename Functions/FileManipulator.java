package Functions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManipulator {
    public String readFile(String filePath, int lineStart, int lineEnd) {
        StringBuilder finalString = new StringBuilder();
        try {
            BufferedReader inputstream = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            int count = 1;
            while(true){
                if ((count >= lineStart) && (count <= lineEnd)) {
                    String input=inputstream.readLine();
                    finalString.append(input).append("\n");
                } else if (count < lineEnd) {
                    inputstream.readLine();
                } else {
                    break;
                }
                count++;
            }
            inputstream.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return finalString.toString();
    }
}
