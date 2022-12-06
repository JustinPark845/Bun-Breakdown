package Functions;

import java.util.concurrent.TimeUnit;

public class TimeManipulator implements Manipulator {
    public void sleepMilliseconds(int t) {
        try {
            TimeUnit.MILLISECONDS.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
