package Functions;

public class StringManipulator implements Manipulator{
    public String removeLastNewLine(String string) {
        return string.substring(0,string.length()-2);
    }
}
