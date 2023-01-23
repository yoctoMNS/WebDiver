package jp.xdomain.html.yoctomns.game;

public class GameLoop implements Runnable {
    public static final long ONE_SEC_NANO_TIME = 1000000000;
    public static final int UPDATES_PER_SOCOND = 60;
    private static final int ONE_SEC = 1;

    private final int DEFAULT_FPS = 60;

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

        try {
            while (running) {
                long now = System.nanoTime();
                delta += (now - last) / targetFrame;
                last = now;

                if (delta >= ONE_SEC) {
                        update();
                        draw();

                    delta--;
                }
            }
        } catch (RuntimeException e) {
            // TODO
            // LoggingUtil.severePrint("ゲーム中に致命的な例外が発生しました。ゲームを終了します。", e);
        } catch (Error e) {
            // TODO
            // LoggingUtil.severePrint("システムに致命的ながエラー発生しました。ゲームを終了します。", e);
        } finally {
            // TODO
            // LoggingUtil.infoPrint("ゲームを終了しました======================");
        }
    }

    private void update() {
        game.update();
    }

    private void draw() {
        game.draw();
    }
}
