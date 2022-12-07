import Components.BunBreakdownFacade;

public class Main {
    public static void main(String args[]) {
        BunBreakdownFacade bunBreakdownFacade = new BunBreakdownFacade();
        bunBreakdownFacade.setUp();
        bunBreakdownFacade.play();
        bunBreakdownFacade.close();
    }
}