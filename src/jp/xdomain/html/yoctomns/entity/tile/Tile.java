package jp.xdomain.html.yoctomns.entity.tile;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.entity.Entity;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.input.Keyboard;

public abstract class Tile extends Entity {
    public Tile(Game game, Position position, Size size, String name, int scale) {
        super(game, position, size, name, scale);
    }

    public abstract boolean canWalk();
}
