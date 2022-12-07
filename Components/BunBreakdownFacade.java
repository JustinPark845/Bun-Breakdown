package Components;

public class BunBreakdownFacade {
    private BunBreakdown bunBreakdown;

    public BunBreakdownFacade() {
        bunBreakdown = new BunBreakdown();
    }

    public void setUp() {
        bunBreakdown.setUp();
    }
    public void play() {
        bunBreakdown.play();
    }

    public void close() {
        bunBreakdown.close();
    }
}