package jp.xdomain.html.yoctomns;

import jp.xdomain.html.yoctomns.game.GameLoop;
import jp.xdomain.html.yoctomns.util.LoggingUtil;

public class GameLauncher {
    public GameLauncher() {
        LoggingUtil.infoPrint("The game has started.");
        Thread thread = new Thread(new GameLoop(), "Game Main Thread");
        thread.run();
    }

    public static void main(String[] args) {
        new GameLauncher();
    }
}
