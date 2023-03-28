package jp.xdomain.html.yoctomns.game;

import jp.xdomain.html.yoctomns.util.LoggingUtil;

public class GameLoop implements Runnable {
    public static final long ONE_SEC_NANO_TIME = 1000000000;
    public static final int UPDATES_PER_SOCOND = 60;
    public static final int ONE_SEC = 1;
    public static final int DEFAULT_FPS = 60;
    public static int dispCounter = 0;
    private boolean running;
    private double targetFrame;
    private Game game;

    public GameLoop() {
        this.running = true;
        this.targetFrame = ONE_SEC_NANO_TIME / DEFAULT_FPS;
        this.game = new Game();
    }

    @Override
    public void run() {
        long last = System.nanoTime();
        double delta = 0;
        long timer = 0;
        int counter = 0;
        try {
            while (running) {
                long now = System.nanoTime();
                delta += (now - last) / targetFrame;
                timer += (now - last);
                last = now;
                if (delta >= ONE_SEC) {
                    update();
                    draw();
                    delta--;
                    counter++;
                }
                if (timer >= ONE_SEC_NANO_TIME) {
                    dispCounter = counter;
                    counter = 0;
                    timer = 0;
                }
            }
        } catch (RuntimeException e) {
            LoggingUtil.severePrint("A fatal exception occurred during game execution. The game will be terminated.", e);
        } catch (Exception e) {
            LoggingUtil.severePrint("A fatal exception occurred during game execution. The game will be terminated.", e);
        } catch (Error e) {
            LoggingUtil.severePrint("A fatal error occurred in the system. The game will be terminated.", e);
        } finally {
            LoggingUtil.infoPrint("The game is closed.");
            LoggingUtil.close();
        }
    }

    private void update() {
        game.update();
    }

    private void draw() {
        game.draw();
    }
}
