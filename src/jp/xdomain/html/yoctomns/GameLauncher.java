package jp.xdomain.html.yoctomns;

import jp.xdomain.html.yoctomns.game.GameLoop;

public class GameLauncher {
    public GameLauncher() {
        Thread thread = new Thread(new GameLoop(), "Game Main Thread");
        thread.run();
    }

    public static void main(String[] args) {
        new GameLauncher();
    }
}
