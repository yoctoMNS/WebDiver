package jp.xdomain.html.yoctomns.state;

import java.awt.Graphics2D;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.input.Keyboard;

public abstract class State {
    protected Game game;
    protected Keyboard keyboard;
    protected State nextState;

    public State(Game game) {
        this.game = game;
        this.keyboard = game.getKeyboard();
        this.nextState = null;
    }

    public void update() {
        if (nextState != null) {
            game.sendState(nextState);
        }
    }

    public abstract void draw(Graphics2D graphics2D);

    public Game getGame() {
        return game;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }
}
